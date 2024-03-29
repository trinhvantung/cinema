// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: show.proto

package vn.trinhtung.grpc;

public interface ShowResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:vn.trinhtung.grpc.ShowResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>.google.protobuf.Timestamp start = 2;</code>
   * @return Whether the start field is set.
   */
  boolean hasStart();
  /**
   * <code>.google.protobuf.Timestamp start = 2;</code>
   * @return The start.
   */
  com.google.protobuf.Timestamp getStart();
  /**
   * <code>.google.protobuf.Timestamp start = 2;</code>
   */
  com.google.protobuf.TimestampOrBuilder getStartOrBuilder();

  /**
   * <code>.google.protobuf.Timestamp end = 3;</code>
   * @return Whether the end field is set.
   */
  boolean hasEnd();
  /**
   * <code>.google.protobuf.Timestamp end = 3;</code>
   * @return The end.
   */
  com.google.protobuf.Timestamp getEnd();
  /**
   * <code>.google.protobuf.Timestamp end = 3;</code>
   */
  com.google.protobuf.TimestampOrBuilder getEndOrBuilder();

  /**
   * <code>int32 movie_id = 4;</code>
   * @return The movieId.
   */
  int getMovieId();

  /**
   * <code>int32 hall_id = 5;</code>
   * @return The hallId.
   */
  int getHallId();

  /**
   * <code>int32 cinema_id = 6;</code>
   * @return The cinemaId.
   */
  int getCinemaId();

  /**
   * <code>repeated .vn.trinhtung.grpc.ShowPriceResponse show_prices = 7;</code>
   */
  java.util.List<vn.trinhtung.grpc.ShowPriceResponse> 
      getShowPricesList();
  /**
   * <code>repeated .vn.trinhtung.grpc.ShowPriceResponse show_prices = 7;</code>
   */
  vn.trinhtung.grpc.ShowPriceResponse getShowPrices(int index);
  /**
   * <code>repeated .vn.trinhtung.grpc.ShowPriceResponse show_prices = 7;</code>
   */
  int getShowPricesCount();
  /**
   * <code>repeated .vn.trinhtung.grpc.ShowPriceResponse show_prices = 7;</code>
   */
  java.util.List<? extends vn.trinhtung.grpc.ShowPriceResponseOrBuilder> 
      getShowPricesOrBuilderList();
  /**
   * <code>repeated .vn.trinhtung.grpc.ShowPriceResponse show_prices = 7;</code>
   */
  vn.trinhtung.grpc.ShowPriceResponseOrBuilder getShowPricesOrBuilder(
      int index);

  /**
   * <code>.vn.trinhtung.grpc.MovieResponse movie = 8;</code>
   * @return Whether the movie field is set.
   */
  boolean hasMovie();
  /**
   * <code>.vn.trinhtung.grpc.MovieResponse movie = 8;</code>
   * @return The movie.
   */
  vn.trinhtung.grpc.MovieResponse getMovie();
  /**
   * <code>.vn.trinhtung.grpc.MovieResponse movie = 8;</code>
   */
  vn.trinhtung.grpc.MovieResponseOrBuilder getMovieOrBuilder();
}
