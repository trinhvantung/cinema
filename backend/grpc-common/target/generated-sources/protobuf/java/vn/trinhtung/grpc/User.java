// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package vn.trinhtung.grpc;

public final class User {
  private User() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vn_trinhtung_grpc_UserResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vn_trinhtung_grpc_UserResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vn_trinhtung_grpc_GetUsersByIdsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vn_trinhtung_grpc_GetUsersByIdsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vn_trinhtung_grpc_GetUsersByIdsResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vn_trinhtung_grpc_GetUsersByIdsResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nuser.proto\022\021vn.trinhtung.grpc\"P\n\014UserR" +
      "esponse\022\n\n\002id\030\001 \001(\t\022\022\n\nfirst_name\030\002 \001(\t\022" +
      "\021\n\tlast_name\030\003 \001(\t\022\r\n\005email\030\004 \001(\t\"#\n\024Get" +
      "UsersByIdsRequest\022\013\n\003ids\030\001 \003(\t\"G\n\025GetUse" +
      "rsByIdsResponse\022.\n\005users\030\001 \003(\0132\037.vn.trin" +
      "htung.grpc.UserResponse2q\n\013UserService\022b" +
      "\n\rgetUsersByIds\022\'.vn.trinhtung.grpc.GetU" +
      "sersByIdsRequest\032(.vn.trinhtung.grpc.Get" +
      "UsersByIdsResponseB\025\n\021vn.trinhtung.grpcP" +
      "\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_vn_trinhtung_grpc_UserResponse_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_vn_trinhtung_grpc_UserResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vn_trinhtung_grpc_UserResponse_descriptor,
        new java.lang.String[] { "Id", "FirstName", "LastName", "Email", });
    internal_static_vn_trinhtung_grpc_GetUsersByIdsRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_vn_trinhtung_grpc_GetUsersByIdsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vn_trinhtung_grpc_GetUsersByIdsRequest_descriptor,
        new java.lang.String[] { "Ids", });
    internal_static_vn_trinhtung_grpc_GetUsersByIdsResponse_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_vn_trinhtung_grpc_GetUsersByIdsResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vn_trinhtung_grpc_GetUsersByIdsResponse_descriptor,
        new java.lang.String[] { "Users", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
