package vn.trinhtung.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.dto.SeatTypeRequestDTO;
import vn.trinhtung.dto.SeatTypeResponseDTO;
import vn.trinhtung.entity.SeatType;
import vn.trinhtung.exception.ApplicationException;
import vn.trinhtung.grpc.SeatTypeResponse;
import vn.trinhtung.mapper.SeatTypeMapper;
import vn.trinhtung.repository.HallRepository;
import vn.trinhtung.repository.SeatTypeRepository;
import vn.trinhtung.service.SeatTypeService;

@RequiredArgsConstructor
@Service
public class SeatTypeServiceImpl implements SeatTypeService {
    private final SeatTypeRepository seatTypeRepository;
    private final HallRepository hallRepository;
    private final SeatTypeMapper seatTypeMapper;

    @Override
    public List<SeatTypeResponseDTO> getAll() {
        return seatTypeRepository.findAll().stream().map(seatTypeMapper::seatTypeToSeatTypeResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SeatTypeResponseDTO getById(Integer id) {
        SeatType seatType = seatTypeRepository.findById(id).orElseThrow(
                () -> new ApplicationException("seat_type_not_found", "Seat type not found"));

        return seatTypeMapper.seatTypeToSeatTypeResponseDTO(seatType);
    }

    @Override
    public void create(SeatTypeRequestDTO request) {
        if (seatTypeRepository.findByName(request.getName()).isPresent()) {
            throw new ApplicationException("duplicate_seat_type_name", "Duplicate seat type name");
        }

        SeatType seatType = seatTypeMapper.seatTypeRequestDTOToSeatType(request);
        seatTypeRepository.save(seatType);
    }

    @Override
    public void update(Integer seatTypeId, SeatTypeRequestDTO request) {
        SeatType seatType = seatTypeRepository.findById(seatTypeId).orElseThrow();

        Optional<SeatType> seatTypeByName = seatTypeRepository.findByName(request.getName());
        if (seatTypeByName.isPresent() && seatTypeByName.get().getId() != seatTypeId) {
            throw new ApplicationException("duplicate_seat_type_name", "Duplicate seat type name");
        }

        seatType.setColor(request.getColor());
        seatType.setName(request.getName());

        seatTypeRepository.save(seatType);
    }

    @Override
    public void delete(Integer seatTypeId) {
        SeatType seatType = seatTypeRepository.findById(seatTypeId).orElseThrow();

        seatTypeRepository.delete(seatType);
    }

    @Override
    public List<SeatTypeResponseDTO> getAllByHall(Integer hallId) {
        return hallRepository.findAllSeatTypesByHallId(hallId).stream()
                .map(seatTypeMapper::seatTypeToSeatTypeResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<SeatTypeResponse> getSeatTypesByHall(Integer hallId) {
        return hallRepository.findAllSeatTypesByHallId(hallId).stream()
                .map(seatTypeMapper::seatTypeToSeatTypeResponse).collect(Collectors.toList());
    }
}
