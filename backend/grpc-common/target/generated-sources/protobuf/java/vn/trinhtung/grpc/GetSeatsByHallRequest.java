// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: seat.proto

package vn.trinhtung.grpc;

/**
 * Protobuf type {@code vn.trinhtung.grpc.GetSeatsByHallRequest}
 */
public final class GetSeatsByHallRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:vn.trinhtung.grpc.GetSeatsByHallRequest)
    GetSeatsByHallRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetSeatsByHallRequest.newBuilder() to construct.
  private GetSeatsByHallRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetSeatsByHallRequest() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetSeatsByHallRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetSeatsByHallRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            hallId_ = input.readInt32();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return vn.trinhtung.grpc.Seat.internal_static_vn_trinhtung_grpc_GetSeatsByHallRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return vn.trinhtung.grpc.Seat.internal_static_vn_trinhtung_grpc_GetSeatsByHallRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            vn.trinhtung.grpc.GetSeatsByHallRequest.class, vn.trinhtung.grpc.GetSeatsByHallRequest.Builder.class);
  }

  public static final int HALL_ID_FIELD_NUMBER = 1;
  private int hallId_;
  /**
   * <code>int32 hall_id = 1;</code>
   * @return The hallId.
   */
  @java.lang.Override
  public int getHallId() {
    return hallId_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (hallId_ != 0) {
      output.writeInt32(1, hallId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (hallId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, hallId_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof vn.trinhtung.grpc.GetSeatsByHallRequest)) {
      return super.equals(obj);
    }
    vn.trinhtung.grpc.GetSeatsByHallRequest other = (vn.trinhtung.grpc.GetSeatsByHallRequest) obj;

    if (getHallId()
        != other.getHallId()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + HALL_ID_FIELD_NUMBER;
    hash = (53 * hash) + getHallId();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static vn.trinhtung.grpc.GetSeatsByHallRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.trinhtung.grpc.GetSeatsByHallRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.trinhtung.grpc.GetSeatsByHallRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.trinhtung.grpc.GetSeatsByHallRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.trinhtung.grpc.GetSeatsByHallRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.trinhtung.grpc.GetSeatsByHallRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.trinhtung.grpc.GetSeatsByHallRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static vn.trinhtung.grpc.GetSeatsByHallRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static vn.trinhtung.grpc.GetSeatsByHallRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static vn.trinhtung.grpc.GetSeatsByHallRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static vn.trinhtung.grpc.GetSeatsByHallRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static vn.trinhtung.grpc.GetSeatsByHallRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(vn.trinhtung.grpc.GetSeatsByHallRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code vn.trinhtung.grpc.GetSeatsByHallRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:vn.trinhtung.grpc.GetSeatsByHallRequest)
      vn.trinhtung.grpc.GetSeatsByHallRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return vn.trinhtung.grpc.Seat.internal_static_vn_trinhtung_grpc_GetSeatsByHallRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return vn.trinhtung.grpc.Seat.internal_static_vn_trinhtung_grpc_GetSeatsByHallRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              vn.trinhtung.grpc.GetSeatsByHallRequest.class, vn.trinhtung.grpc.GetSeatsByHallRequest.Builder.class);
    }

    // Construct using vn.trinhtung.grpc.GetSeatsByHallRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      hallId_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return vn.trinhtung.grpc.Seat.internal_static_vn_trinhtung_grpc_GetSeatsByHallRequest_descriptor;
    }

    @java.lang.Override
    public vn.trinhtung.grpc.GetSeatsByHallRequest getDefaultInstanceForType() {
      return vn.trinhtung.grpc.GetSeatsByHallRequest.getDefaultInstance();
    }

    @java.lang.Override
    public vn.trinhtung.grpc.GetSeatsByHallRequest build() {
      vn.trinhtung.grpc.GetSeatsByHallRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public vn.trinhtung.grpc.GetSeatsByHallRequest buildPartial() {
      vn.trinhtung.grpc.GetSeatsByHallRequest result = new vn.trinhtung.grpc.GetSeatsByHallRequest(this);
      result.hallId_ = hallId_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof vn.trinhtung.grpc.GetSeatsByHallRequest) {
        return mergeFrom((vn.trinhtung.grpc.GetSeatsByHallRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(vn.trinhtung.grpc.GetSeatsByHallRequest other) {
      if (other == vn.trinhtung.grpc.GetSeatsByHallRequest.getDefaultInstance()) return this;
      if (other.getHallId() != 0) {
        setHallId(other.getHallId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      vn.trinhtung.grpc.GetSeatsByHallRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (vn.trinhtung.grpc.GetSeatsByHallRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int hallId_ ;
    /**
     * <code>int32 hall_id = 1;</code>
     * @return The hallId.
     */
    @java.lang.Override
    public int getHallId() {
      return hallId_;
    }
    /**
     * <code>int32 hall_id = 1;</code>
     * @param value The hallId to set.
     * @return This builder for chaining.
     */
    public Builder setHallId(int value) {
      
      hallId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 hall_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearHallId() {
      
      hallId_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:vn.trinhtung.grpc.GetSeatsByHallRequest)
  }

  // @@protoc_insertion_point(class_scope:vn.trinhtung.grpc.GetSeatsByHallRequest)
  private static final vn.trinhtung.grpc.GetSeatsByHallRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new vn.trinhtung.grpc.GetSeatsByHallRequest();
  }

  public static vn.trinhtung.grpc.GetSeatsByHallRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetSeatsByHallRequest>
      PARSER = new com.google.protobuf.AbstractParser<GetSeatsByHallRequest>() {
    @java.lang.Override
    public GetSeatsByHallRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetSeatsByHallRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetSeatsByHallRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetSeatsByHallRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public vn.trinhtung.grpc.GetSeatsByHallRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

