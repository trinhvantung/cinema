package vn.trinhtung.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.trinhtung.dto.SeatRequestDTO;
import vn.trinhtung.dto.SeatResponseDTO;
import vn.trinhtung.entity.Hall;
import vn.trinhtung.entity.Seat;
import vn.trinhtung.exception.ApplicationException;
import vn.trinhtung.grpc.SeatResponse;
import vn.trinhtung.mapper.SeatMapper;
import vn.trinhtung.repository.HallRepository;
import vn.trinhtung.repository.SeatRepository;
import vn.trinhtung.service.SeatService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    private final HallRepository hallRepository;

    @Override
    public List<SeatResponseDTO> getAllByHall(Integer hallId) {
        return seatRepository.findAllByHallId(hallId).stream().map(seatMapper::seatToSeatResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SeatResponse> getSeatsByHall(Integer hallId) {
        return seatRepository.findAllByHallId(hallId).stream().map(seatMapper::seatToSeatResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SeatResponse> getSeatsByIds(List<Integer> ids) {
        return seatRepository.findAllById(ids).stream().map(seatMapper::seatToSeatResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void save(Integer hallId, List<SeatRequestDTO> seatRequestDTOs) {
        Hall hall = hallRepository.findById(hallId)
                .orElseThrow(() -> new ApplicationException("hall_not_found", "Hall not found"));

        hall.getSeats().clear();

        seatRequestDTOs.forEach(s -> {
            Seat seat = seatMapper.seatRequestDTOToSeat(s);
            seat.setHall(hall);
            hall.getSeats().add(seat);
        });

        hallRepository.save(hall);
    }
}
