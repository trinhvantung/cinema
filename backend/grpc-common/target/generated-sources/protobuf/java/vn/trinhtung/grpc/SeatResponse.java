// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: seat.proto

package vn.trinhtung.grpc;

/**
 * Protobuf type {@code vn.trinhtung.grpc.SeatResponse}
 */
public final class SeatResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:vn.trinhtung.grpc.SeatResponse)
    SeatResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SeatResponse.newBuilder() to construct.
  private SeatResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SeatResponse() {
    name_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new SeatResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SeatResponse(
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
          case 16: {

            rowIndex_ = input.readInt32();
            break;
          }
          case 24: {

            columnIndex_ = input.readInt32();
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 42: {
            vn.trinhtung.grpc.SeatTypeResponse.Builder subBuilder = null;
            if (seatType_ != null) {
              subBuilder = seatType_.toBuilder();
            }
            seatType_ = input.readMessage(vn.trinhtung.grpc.SeatTypeResponse.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(seatType_);
              seatType_ = subBuilder.buildPartial();
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
    return vn.trinhtung.grpc.Seat.internal_static_vn_trinhtung_grpc_SeatResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return vn.trinhtung.grpc.Seat.internal_static_vn_trinhtung_grpc_SeatResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            vn.trinhtung.grpc.SeatResponse.class, vn.trinhtung.grpc.SeatResponse.Builder.class);
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

  public static final int ROW_INDEX_FIELD_NUMBER = 2;
  private int rowIndex_;
  /**
   * <code>int32 row_index = 2;</code>
   * @return The rowIndex.
   */
  @java.lang.Override
  public int getRowIndex() {
    return rowIndex_;
  }

  public static final int COLUMN_INDEX_FIELD_NUMBER = 3;
  private int columnIndex_;
  /**
   * <code>int32 column_index = 3;</code>
   * @return The columnIndex.
   */
  @java.lang.Override
  public int getColumnIndex() {
    return columnIndex_;
  }

  public static final int NAME_FIELD_NUMBER = 4;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 4;</code>
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
   * <code>string name = 4;</code>
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

  public static final int SEAT_TYPE_FIELD_NUMBER = 5;
  private vn.trinhtung.grpc.SeatTypeResponse seatType_;
  /**
   * <code>.vn.trinhtung.grpc.SeatTypeResponse seat_type = 5;</code>
   * @return Whether the seatType field is set.
   */
  @java.lang.Override
  public boolean hasSeatType() {
    return seatType_ != null;
  }
  /**
   * <code>.vn.trinhtung.grpc.SeatTypeResponse seat_type = 5;</code>
   * @return The seatType.
   */
  @java.lang.Override
  public vn.trinhtung.grpc.SeatTypeResponse getSeatType() {
    return seatType_ == null ? vn.trinhtung.grpc.SeatTypeResponse.getDefaultInstance() : seatType_;
  }
  /**
   * <code>.vn.trinhtung.grpc.SeatTypeResponse seat_type = 5;</code>
   */
  @java.lang.Override
  public vn.trinhtung.grpc.SeatTypeResponseOrBuilder getSeatTypeOrBuilder() {
    return getSeatType();
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
    if (rowIndex_ != 0) {
      output.writeInt32(2, rowIndex_);
    }
    if (columnIndex_ != 0) {
      output.writeInt32(3, columnIndex_);
    }
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, name_);
    }
    if (seatType_ != null) {
      output.writeMessage(5, getSeatType());
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
    if (rowIndex_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, rowIndex_);
    }
    if (columnIndex_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, columnIndex_);
    }
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, name_);
    }
    if (seatType_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(5, getSeatType());
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
    if (!(obj instanceof vn.trinhtung.grpc.SeatResponse)) {
      return super.equals(obj);
    }
    vn.trinhtung.grpc.SeatResponse other = (vn.trinhtung.grpc.SeatResponse) obj;

    if (getId()
        != other.getId()) return false;
    if (getRowIndex()
        != other.getRowIndex()) return false;
    if (getColumnIndex()
        != other.getColumnIndex()) return false;
    if (!getName()
        .equals(other.getName())) return false;
    if (hasSeatType() != other.hasSeatType()) return false;
    if (hasSeatType()) {
      if (!getSeatType()
          .equals(other.getSeatType())) return false;
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
    hash = (37 * hash) + ROW_INDEX_FIELD_NUMBER;
    hash = (53 * hash) + getRowIndex();
    hash = (37 * hash) + COLUMN_INDEX_FIELD_NUMBER;
    hash = (53 * hash) + getColumnIndex();
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    if (hasSeatType()) {
      hash = (37 * hash) + SEAT_TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getSeatType().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static vn.trinhtung.grpc.SeatResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.trinhtung.grpc.SeatResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.trinhtung.grpc.SeatResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.trinhtung.grpc.SeatResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.trinhtung.grpc.SeatResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.trinhtung.grpc.SeatResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.trinhtung.grpc.SeatResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static vn.trinhtung.grpc.SeatResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static vn.trinhtung.grpc.SeatResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static vn.trinhtung.grpc.SeatResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static vn.trinhtung.grpc.SeatResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static vn.trinhtung.grpc.SeatResponse parseFrom(
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
  public static Builder newBuilder(vn.trinhtung.grpc.SeatResponse prototype) {
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
   * Protobuf type {@code vn.trinhtung.grpc.SeatResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:vn.trinhtung.grpc.SeatResponse)
      vn.trinhtung.grpc.SeatResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return vn.trinhtung.grpc.Seat.internal_static_vn_trinhtung_grpc_SeatResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return vn.trinhtung.grpc.Seat.internal_static_vn_trinhtung_grpc_SeatResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              vn.trinhtung.grpc.SeatResponse.class, vn.trinhtung.grpc.SeatResponse.Builder.class);
    }

    // Construct using vn.trinhtung.grpc.SeatResponse.newBuilder()
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

      rowIndex_ = 0;

      columnIndex_ = 0;

      name_ = "";

      if (seatTypeBuilder_ == null) {
        seatType_ = null;
      } else {
        seatType_ = null;
        seatTypeBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return vn.trinhtung.grpc.Seat.internal_static_vn_trinhtung_grpc_SeatResponse_descriptor;
    }

    @java.lang.Override
    public vn.trinhtung.grpc.SeatResponse getDefaultInstanceForType() {
      return vn.trinhtung.grpc.SeatResponse.getDefaultInstance();
    }

    @java.lang.Override
    public vn.trinhtung.grpc.SeatResponse build() {
      vn.trinhtung.grpc.SeatResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public vn.trinhtung.grpc.SeatResponse buildPartial() {
      vn.trinhtung.grpc.SeatResponse result = new vn.trinhtung.grpc.SeatResponse(this);
      result.id_ = id_;
      result.rowIndex_ = rowIndex_;
      result.columnIndex_ = columnIndex_;
      result.name_ = name_;
      if (seatTypeBuilder_ == null) {
        result.seatType_ = seatType_;
      } else {
        result.seatType_ = seatTypeBuilder_.build();
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
      if (other instanceof vn.trinhtung.grpc.SeatResponse) {
        return mergeFrom((vn.trinhtung.grpc.SeatResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(vn.trinhtung.grpc.SeatResponse other) {
      if (other == vn.trinhtung.grpc.SeatResponse.getDefaultInstance()) return this;
      if (other.getId() != 0) {
        setId(other.getId());
      }
      if (other.getRowIndex() != 0) {
        setRowIndex(other.getRowIndex());
      }
      if (other.getColumnIndex() != 0) {
        setColumnIndex(other.getColumnIndex());
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (other.hasSeatType()) {
        mergeSeatType(other.getSeatType());
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
      vn.trinhtung.grpc.SeatResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (vn.trinhtung.grpc.SeatResponse) e.getUnfinishedMessage();
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

    private int rowIndex_ ;
    /**
     * <code>int32 row_index = 2;</code>
     * @return The rowIndex.
     */
    @java.lang.Override
    public int getRowIndex() {
      return rowIndex_;
    }
    /**
     * <code>int32 row_index = 2;</code>
     * @param value The rowIndex to set.
     * @return This builder for chaining.
     */
    public Builder setRowIndex(int value) {
      
      rowIndex_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 row_index = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearRowIndex() {
      
      rowIndex_ = 0;
      onChanged();
      return this;
    }

    private int columnIndex_ ;
    /**
     * <code>int32 column_index = 3;</code>
     * @return The columnIndex.
     */
    @java.lang.Override
    public int getColumnIndex() {
      return columnIndex_;
    }
    /**
     * <code>int32 column_index = 3;</code>
     * @param value The columnIndex to set.
     * @return This builder for chaining.
     */
    public Builder setColumnIndex(int value) {
      
      columnIndex_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 column_index = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearColumnIndex() {
      
      columnIndex_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 4;</code>
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
     * <code>string name = 4;</code>
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
     * <code>string name = 4;</code>
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
     * <code>string name = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 4;</code>
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

    private vn.trinhtung.grpc.SeatTypeResponse seatType_;
    private com.google.protobuf.SingleFieldBuilderV3<
        vn.trinhtung.grpc.SeatTypeResponse, vn.trinhtung.grpc.SeatTypeResponse.Builder, vn.trinhtung.grpc.SeatTypeResponseOrBuilder> seatTypeBuilder_;
    /**
     * <code>.vn.trinhtung.grpc.SeatTypeResponse seat_type = 5;</code>
     * @return Whether the seatType field is set.
     */
    public boolean hasSeatType() {
      return seatTypeBuilder_ != null || seatType_ != null;
    }
    /**
     * <code>.vn.trinhtung.grpc.SeatTypeResponse seat_type = 5;</code>
     * @return The seatType.
     */
    public vn.trinhtung.grpc.SeatTypeResponse getSeatType() {
      if (seatTypeBuilder_ == null) {
        return seatType_ == null ? vn.trinhtung.grpc.SeatTypeResponse.getDefaultInstance() : seatType_;
      } else {
        return seatTypeBuilder_.getMessage();
      }
    }
    /**
     * <code>.vn.trinhtung.grpc.SeatTypeResponse seat_type = 5;</code>
     */
    public Builder setSeatType(vn.trinhtung.grpc.SeatTypeResponse value) {
      if (seatTypeBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        seatType_ = value;
        onChanged();
      } else {
        seatTypeBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.vn.trinhtung.grpc.SeatTypeResponse seat_type = 5;</code>
     */
    public Builder setSeatType(
        vn.trinhtung.grpc.SeatTypeResponse.Builder builderForValue) {
      if (seatTypeBuilder_ == null) {
        seatType_ = builderForValue.build();
        onChanged();
      } else {
        seatTypeBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.vn.trinhtung.grpc.SeatTypeResponse seat_type = 5;</code>
     */
    public Builder mergeSeatType(vn.trinhtung.grpc.SeatTypeResponse value) {
      if (seatTypeBuilder_ == null) {
        if (seatType_ != null) {
          seatType_ =
            vn.trinhtung.grpc.SeatTypeResponse.newBuilder(seatType_).mergeFrom(value).buildPartial();
        } else {
          seatType_ = value;
        }
        onChanged();
      } else {
        seatTypeBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.vn.trinhtung.grpc.SeatTypeResponse seat_type = 5;</code>
     */
    public Builder clearSeatType() {
      if (seatTypeBuilder_ == null) {
        seatType_ = null;
        onChanged();
      } else {
        seatType_ = null;
        seatTypeBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.vn.trinhtung.grpc.SeatTypeResponse seat_type = 5;</code>
     */
    public vn.trinhtung.grpc.SeatTypeResponse.Builder getSeatTypeBuilder() {
      
      onChanged();
      return getSeatTypeFieldBuilder().getBuilder();
    }
    /**
     * <code>.vn.trinhtung.grpc.SeatTypeResponse seat_type = 5;</code>
     */
    public vn.trinhtung.grpc.SeatTypeResponseOrBuilder getSeatTypeOrBuilder() {
      if (seatTypeBuilder_ != null) {
        return seatTypeBuilder_.getMessageOrBuilder();
      } else {
        return seatType_ == null ?
            vn.trinhtung.grpc.SeatTypeResponse.getDefaultInstance() : seatType_;
      }
    }
    /**
     * <code>.vn.trinhtung.grpc.SeatTypeResponse seat_type = 5;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        vn.trinhtung.grpc.SeatTypeResponse, vn.trinhtung.grpc.SeatTypeResponse.Builder, vn.trinhtung.grpc.SeatTypeResponseOrBuilder> 
        getSeatTypeFieldBuilder() {
      if (seatTypeBuilder_ == null) {
        seatTypeBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            vn.trinhtung.grpc.SeatTypeResponse, vn.trinhtung.grpc.SeatTypeResponse.Builder, vn.trinhtung.grpc.SeatTypeResponseOrBuilder>(
                getSeatType(),
                getParentForChildren(),
                isClean());
        seatType_ = null;
      }
      return seatTypeBuilder_;
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


    // @@protoc_insertion_point(builder_scope:vn.trinhtung.grpc.SeatResponse)
  }

  // @@protoc_insertion_point(class_scope:vn.trinhtung.grpc.SeatResponse)
  private static final vn.trinhtung.grpc.SeatResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new vn.trinhtung.grpc.SeatResponse();
  }

  public static vn.trinhtung.grpc.SeatResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SeatResponse>
      PARSER = new com.google.protobuf.AbstractParser<SeatResponse>() {
    @java.lang.Override
    public SeatResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SeatResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SeatResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SeatResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public vn.trinhtung.grpc.SeatResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

