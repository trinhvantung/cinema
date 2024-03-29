// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: movie.proto

package vn.trinhtung.grpc;

public final class Movie {
  private Movie() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vn_trinhtung_grpc_CategoryResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vn_trinhtung_grpc_CategoryResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vn_trinhtung_grpc_MovieResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vn_trinhtung_grpc_MovieResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vn_trinhtung_grpc_GetMovieByIdRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vn_trinhtung_grpc_GetMovieByIdRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vn_trinhtung_grpc_GetMovieByIdResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vn_trinhtung_grpc_GetMovieByIdResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vn_trinhtung_grpc_GetMoviesByIdsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vn_trinhtung_grpc_GetMoviesByIdsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vn_trinhtung_grpc_GetMoviesByIdsResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vn_trinhtung_grpc_GetMoviesByIdsResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013movie.proto\022\021vn.trinhtung.grpc\032\037google" +
      "/protobuf/timestamp.proto\"C\n\020CategoryRes" +
      "ponse\022\n\n\002id\030\001 \001(\005\022\014\n\004name\030\002 \001(\t\022\025\n\rdispl" +
      "ay_order\030\003 \001(\005\"\314\001\n\rMovieResponse\022\n\n\002id\030\001" +
      " \001(\005\022\014\n\004name\030\002 \001(\t\022\020\n\010duration\030\003 \001(\005\022\023\n\013" +
      "description\030\004 \001(\t\022\021\n\tthumbnail\030\005 \001(\t\0220\n\014" +
      "release_date\030\006 \001(\0132\032.google.protobuf.Tim" +
      "estamp\0225\n\010category\030\007 \003(\0132#.vn.trinhtung." +
      "grpc.CategoryResponse\"!\n\023GetMovieByIdReq" +
      "uest\022\n\n\002id\030\001 \001(\005\"G\n\024GetMovieByIdResponse" +
      "\022/\n\005movie\030\001 \001(\0132 .vn.trinhtung.grpc.Movi" +
      "eResponse\"$\n\025GetMoviesByIdsRequest\022\013\n\003id" +
      "s\030\001 \003(\005\"J\n\026GetMoviesByIdsResponse\0220\n\006mov" +
      "ies\030\001 \003(\0132 .vn.trinhtung.grpc.MovieRespo" +
      "nse2\326\001\n\014MovieService\022_\n\014getMovieById\022&.v" +
      "n.trinhtung.grpc.GetMovieByIdRequest\032\'.v" +
      "n.trinhtung.grpc.GetMovieByIdResponse\022e\n" +
      "\016getMoviesByIds\022(.vn.trinhtung.grpc.GetM" +
      "oviesByIdsRequest\032).vn.trinhtung.grpc.Ge" +
      "tMoviesByIdsResponseB\025\n\021vn.trinhtung.grp" +
      "cP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.TimestampProto.getDescriptor(),
        });
    internal_static_vn_trinhtung_grpc_CategoryResponse_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_vn_trinhtung_grpc_CategoryResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vn_trinhtung_grpc_CategoryResponse_descriptor,
        new java.lang.String[] { "Id", "Name", "DisplayOrder", });
    internal_static_vn_trinhtung_grpc_MovieResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_vn_trinhtung_grpc_MovieResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vn_trinhtung_grpc_MovieResponse_descriptor,
        new java.lang.String[] { "Id", "Name", "Duration", "Description", "Thumbnail", "ReleaseDate", "Category", });
    internal_static_vn_trinhtung_grpc_GetMovieByIdRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_vn_trinhtung_grpc_GetMovieByIdRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vn_trinhtung_grpc_GetMovieByIdRequest_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_vn_trinhtung_grpc_GetMovieByIdResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_vn_trinhtung_grpc_GetMovieByIdResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vn_trinhtung_grpc_GetMovieByIdResponse_descriptor,
        new java.lang.String[] { "Movie", });
    internal_static_vn_trinhtung_grpc_GetMoviesByIdsRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_vn_trinhtung_grpc_GetMoviesByIdsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vn_trinhtung_grpc_GetMoviesByIdsRequest_descriptor,
        new java.lang.String[] { "Ids", });
    internal_static_vn_trinhtung_grpc_GetMoviesByIdsResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_vn_trinhtung_grpc_GetMoviesByIdsResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vn_trinhtung_grpc_GetMoviesByIdsResponse_descriptor,
        new java.lang.String[] { "Movies", });
    com.google.protobuf.TimestampProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
