// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cinema.proto

package vn.trinhtung.grpc;

public final class Cinema {
  private Cinema() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vn_trinhtung_grpc_GetCinemasByIdsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vn_trinhtung_grpc_GetCinemasByIdsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vn_trinhtung_grpc_GetCinemasByIdsResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vn_trinhtung_grpc_GetCinemasByIdsResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vn_trinhtung_grpc_CinemaResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vn_trinhtung_grpc_CinemaResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014cinema.proto\022\021vn.trinhtung.grpc\"%\n\026Get" +
      "CinemasByIdsRequest\022\013\n\003ids\030\001 \003(\005\"M\n\027GetC" +
      "inemasByIdsResponse\0222\n\007cinemas\030\001 \003(\0132!.v" +
      "n.trinhtung.grpc.CinemaResponse\";\n\016Cinem" +
      "aResponse\022\n\n\002id\030\001 \001(\005\022\014\n\004name\030\002 \001(\t\022\017\n\007a" +
      "ddress\030\003 \001(\t2y\n\rCinemaService\022h\n\017getCine" +
      "masByIds\022).vn.trinhtung.grpc.GetCinemasB" +
      "yIdsRequest\032*.vn.trinhtung.grpc.GetCinem" +
      "asByIdsResponseB\025\n\021vn.trinhtung.grpcP\001b\006" +
      "proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_vn_trinhtung_grpc_GetCinemasByIdsRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_vn_trinhtung_grpc_GetCinemasByIdsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vn_trinhtung_grpc_GetCinemasByIdsRequest_descriptor,
        new java.lang.String[] { "Ids", });
    internal_static_vn_trinhtung_grpc_GetCinemasByIdsResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_vn_trinhtung_grpc_GetCinemasByIdsResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vn_trinhtung_grpc_GetCinemasByIdsResponse_descriptor,
        new java.lang.String[] { "Cinemas", });
    internal_static_vn_trinhtung_grpc_CinemaResponse_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_vn_trinhtung_grpc_CinemaResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vn_trinhtung_grpc_CinemaResponse_descriptor,
        new java.lang.String[] { "Id", "Name", "Address", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}