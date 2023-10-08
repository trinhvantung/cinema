// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hall.proto

package vn.trinhtung.grpc;

/**
 * Protobuf type {@code vn.trinhtung.grpc.HallResponse}
 */
public final class HallResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:vn.trinhtung.grpc.HallResponse)
    HallResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use HallResponse.newBuilder() to construct.
  private HallResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private HallResponse() {
    name_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new HallResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private HallResponse(
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

            id_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 26: {
            vn.trinhtung.grpc.CinemaResponse.Builder subBuilder = null;
            if (cinema_ != null) {
              subBuilder = cinema_.toBuilder();
            }
            cinema_ = input.readMessage(vn.trinhtung.grpc.CinemaResponse.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(cinema_);
              cinema_ = subBuilder.buildPartial();
            }

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
    return vn.trinhtung.grpc.Hall.internal_static_vn_trinhtung_grpc_HallResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return vn.trinhtung.grpc.Hall.internal_static_vn_trinhtung_grpc_HallResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            vn.trinhtung.grpc.HallResponse.class, vn.trinhtung.grpc.HallResponse.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private int id_;
  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  @java.lang.Override
  public int getId() {
    return id_;
  }

  public static final int NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  @java.lang.Override
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CINEMA_FIELD_NUMBER = 3;
  private vn.trinhtung.grpc.CinemaResponse cinema_;
  /**
   * <code>.vn.trinhtung.grpc.CinemaResponse cinema = 3;</code>
   * @return Whether the cinema field is set.
   */
  @java.lang.Override
  public boolean hasCinema() {
    return cinema_ != null;
  }
  /**
   * <code>.vn.trinhtung.grpc.CinemaResponse cinema = 3;</code>
   * @return The cinema.
   */
  @java.lang.Override
  public vn.trinhtung.grpc.CinemaResponse getCinema() {
    return cinema_ == null ? vn.trinhtung.grpc.CinemaResponse.getDefaultInstance() : cinema_;
  }
  /**
   * <code>.vn.trinhtung.grpc.CinemaResponse cinema = 3;</code>
   */
  @java.lang.Override
  public vn.trinhtung.grpc.CinemaResponseOrBuilder getCinemaOrBuilder() {
    return getCinema();
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
    if (id_ != 0) {
      output.writeInt32(1, id_);
    }
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
    }
    if (cinema_ != null) {
      output.writeMessage(3, getCinema());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, id_);
    }
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
    }
    if (cinema_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getCinema());
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
    if (!(obj instanceof vn.trinhtung.grpc.HallResponse)) {
      return super.equals(obj);
    }
    vn.trinhtung.grpc.HallResponse other = (vn.trinhtung.grpc.HallResponse) obj;

    if (getId()
        != other.getId()) return false;
    if (!getName()
        .equals(other.getName())) return false;
    if (hasCinema() != other.hasCinema()) return false;
    if (hasCinema()) {
      if (!getCinema()
          .equals(other.getCinema())) return false;
    }
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
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId();
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    if (hasCinema()) {
      hash = (37 * hash) + CINEMA_FIELD_NUMBER;
      hash = (53 * hash) + getCinema().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static vn.trinhtung.grpc.HallResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.trinhtung.grpc.HallResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.trinhtung.grpc.HallResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.trinhtung.grpc.HallResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.trinhtung.grpc.HallResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.trinhtung.grpc.HallResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.trinhtung.grpc.HallResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static vn.trinhtung.grpc.HallResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static vn.trinhtung.grpc.HallResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static vn.trinhtung.grpc.HallResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static vn.trinhtung.grpc.HallResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static vn.trinhtung.grpc.HallResponse parseFrom(
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
  public static Builder newBuilder(vn.trinhtung.grpc.HallResponse prototype) {
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
   * Protobuf type {@code vn.trinhtung.grpc.HallResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:vn.trinhtung.grpc.HallResponse)
      vn.trinhtung.grpc.HallResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return vn.trinhtung.grpc.Hall.internal_static_vn_trinhtung_grpc_HallResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return vn.trinhtung.grpc.Hall.internal_static_vn_trinhtung_grpc_HallResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              vn.trinhtung.grpc.HallResponse.class, vn.trinhtung.grpc.HallResponse.Builder.class);
    }

    // Construct using vn.trinhtung.grpc.HallResponse.newBuilder()
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
      id_ = 0;

      name_ = "";

      if (cinemaBuilder_ == null) {
        cinema_ = null;
      } else {
        cinema_ = null;
        cinemaBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return vn.trinhtung.grpc.Hall.internal_static_vn_trinhtung_grpc_HallResponse_descriptor;
    }

    @java.lang.Override
    public vn.trinhtung.grpc.HallResponse getDefaultInstanceForType() {
      return vn.trinhtung.grpc.HallResponse.getDefaultInstance();
    }

    @java.lang.Override
    public vn.trinhtung.grpc.HallResponse build() {
      vn.trinhtung.grpc.HallResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public vn.trinhtung.grpc.HallResponse buildPartial() {
      vn.trinhtung.grpc.HallResponse result = new vn.trinhtung.grpc.HallResponse(this);
      result.id_ = id_;
      result.name_ = name_;
      if (cinemaBuilder_ == null) {
        result.cinema_ = cinema_;
      } else {
        result.cinema_ = cinemaBuilder_.build();
      }
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
      if (other instanceof vn.trinhtung.grpc.HallResponse) {
        return mergeFrom((vn.trinhtung.grpc.HallResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(vn.trinhtung.grpc.HallResponse other) {
      if (other == vn.trinhtung.grpc.HallResponse.getDefaultInstance()) return this;
      if (other.getId() != 0) {
        setId(other.getId());
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (other.hasCinema()) {
        mergeCinema(other.getCinema());
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
      vn.trinhtung.grpc.HallResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (vn.trinhtung.grpc.HallResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int id_ ;
    /**
     * <code>int32 id = 1;</code>
     * @return The id.
     */
    @java.lang.Override
    public int getId() {
      return id_;
    }
    /**
     * <code>int32 id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(int value) {
      
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      
      id_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 2;</code>
     * @return The name.
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @param value The name to set.
     * @return This builder for chaining.
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     * @param value The bytes for name to set.
     * @return This builder for chaining.
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private vn.trinhtung.grpc.CinemaResponse cinema_;
    private com.google.protobuf.SingleFieldBuilderV3<
        vn.trinhtung.grpc.CinemaResponse, vn.trinhtung.grpc.CinemaResponse.Builder, vn.trinhtung.grpc.CinemaResponseOrBuilder> cinemaBuilder_;
    /**
     * <code>.vn.trinhtung.grpc.CinemaResponse cinema = 3;</code>
     * @return Whether the cinema field is set.
     */
    public boolean hasCinema() {
      return cinemaBuilder_ != null || cinema_ != null;
    }
    /**
     * <code>.vn.trinhtung.grpc.CinemaResponse cinema = 3;</code>
     * @return The cinema.
     */
    public vn.trinhtung.grpc.CinemaResponse getCinema() {
      if (cinemaBuilder_ == null) {
        return cinema_ == null ? vn.trinhtung.grpc.CinemaResponse.getDefaultInstance() : cinema_;
      } else {
        return cinemaBuilder_.getMessage();
      }
    }
    /**
     * <code>.vn.trinhtung.grpc.CinemaResponse cinema = 3;</code>
     */
    public Builder setCinema(vn.trinhtung.grpc.CinemaResponse value) {
      if (cinemaBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        cinema_ = value;
        onChanged();
      } else {
        cinemaBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.vn.trinhtung.grpc.CinemaResponse cinema = 3;</code>
     */
    public Builder setCinema(
        vn.trinhtung.grpc.CinemaResponse.Builder builderForValue) {
      if (cinemaBuilder_ == null) {
        cinema_ = builderForValue.build();
        onChanged();
      } else {
        cinemaBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.vn.trinhtung.grpc.CinemaResponse cinema = 3;</code>
     */
    public Builder mergeCinema(vn.trinhtung.grpc.CinemaResponse value) {
      if (cinemaBuilder_ == null) {
        if (cinema_ != null) {
          cinema_ =
            vn.trinhtung.grpc.CinemaResponse.newBuilder(cinema_).mergeFrom(value).buildPartial();
        } else {
          cinema_ = value;
        }
        onChanged();
      } else {
        cinemaBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.vn.trinhtung.grpc.CinemaResponse cinema = 3;</code>
     */
    public Builder clearCinema() {
      if (cinemaBuilder_ == null) {
        cinema_ = null;
        onChanged();
      } else {
        cinema_ = null;
        cinemaBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.vn.trinhtung.grpc.CinemaResponse cinema = 3;</code>
     */
    public vn.trinhtung.grpc.CinemaResponse.Builder getCinemaBuilder() {
      
      onChanged();
      return getCinemaFieldBuilder().getBuilder();
    }
    /**
     * <code>.vn.trinhtung.grpc.CinemaResponse cinema = 3;</code>
     */
    public vn.trinhtung.grpc.CinemaResponseOrBuilder getCinemaOrBuilder() {
      if (cinemaBuilder_ != null) {
        return cinemaBuilder_.getMessageOrBuilder();
      } else {
        return cinema_ == null ?
            vn.trinhtung.grpc.CinemaResponse.getDefaultInstance() : cinema_;
      }
    }
    /**
     * <code>.vn.trinhtung.grpc.CinemaResponse cinema = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        vn.trinhtung.grpc.CinemaResponse, vn.trinhtung.grpc.CinemaResponse.Builder, vn.trinhtung.grpc.CinemaResponseOrBuilder> 
        getCinemaFieldBuilder() {
      if (cinemaBuilder_ == null) {
        cinemaBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            vn.trinhtung.grpc.CinemaResponse, vn.trinhtung.grpc.CinemaResponse.Builder, vn.trinhtung.grpc.CinemaResponseOrBuilder>(
                getCinema(),
                getParentForChildren(),
                isClean());
        cinema_ = null;
      }
      return cinemaBuilder_;
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


    // @@protoc_insertion_point(builder_scope:vn.trinhtung.grpc.HallResponse)
  }

  // @@protoc_insertion_point(class_scope:vn.trinhtung.grpc.HallResponse)
  private static final vn.trinhtung.grpc.HallResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new vn.trinhtung.grpc.HallResponse();
  }

  public static vn.trinhtung.grpc.HallResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<HallResponse>
      PARSER = new com.google.protobuf.AbstractParser<HallResponse>() {
    @java.lang.Override
    public HallResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new HallResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<HallResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<HallResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public vn.trinhtung.grpc.HallResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

