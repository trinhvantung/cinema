// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: show.proto

package vn.trinhtung.grpc;

public interface ShowPriceResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:vn.trinhtung.grpc.ShowPriceResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 show_id = 1;</code>
   * @return The showId.
   */
  int getShowId();

  /**
   * <code>int32 seat_type_id = 2;</code>
   * @return The seatTypeId.
   */
  int getSeatTypeId();

  /**
   * <code>string price = 3;</code>
   * @return The price.
   */
  java.lang.String getPrice();
  /**
   * <code>string price = 3;</code>
   * @return The bytes for price.
   */
  com.google.protobuf.ByteString
      getPriceBytes();
}