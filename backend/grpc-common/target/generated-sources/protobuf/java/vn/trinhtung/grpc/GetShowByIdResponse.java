// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: show.proto

package vn.trinhtung.grpc;

/**
 * Protobuf type {@code vn.trinhtung.grpc.GetShowByIdResponse}
 */
public final class GetShowByIdResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:vn.trinhtung.grpc.GetShowByIdResponse)
    GetShowByIdResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetShowByIdResponse.newBuilder() to construct.
  private GetShowByIdResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetShowByIdResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetShowByIdResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetShowByIdResponse(
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
          case 10: {
            vn.trinhtung.grpc.ShowResponse.Builder subBuilder = null;
            if (show_ != null) {
              subBuilder = show_.toBuilder();
            }
            show_ = input.readMessage(vn.trinhtung.grpc.ShowResponse.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(show_);
              show_ = subBuilder.buildPartial();
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
    return vn.trinhtung.grpc.Show.internal_static_vn_trinhtung_grpc_GetShowByIdResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return vn.trinhtung.grpc.Show.internal_static_vn_trinhtung_grpc_GetShowByIdResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            vn.trinhtung.grpc.GetShowByIdResponse.class, vn.trinhtung.grpc.GetShowByIdResponse.Builder.class);
  }

  public static final int SHOW_FIELD_NUMBER = 1;
  private vn.trinhtung.grpc.ShowResponse show_;
  /**
   * <code>.vn.trinhtung.grpc.ShowResponse show = 1;</code>
   * @return Whether the show field is set.
   */
  @java.lang.Override
  public boolean hasShow() {
    return show_ != null;
  }
  /**
   * <code>.vn.trinhtung.grpc.ShowResponse show = 1;</code>
   * @return The show.
   */
  @java.lang.Override
  public vn.trinhtung.grpc.ShowResponse getShow() {
    return show_ == null ? vn.trinhtung.grpc.ShowResponse.getDefaultInstance() : show_;
  }
  /**
   * <code>.vn.trinhtung.grpc.ShowResponse show = 1;</code>
   */
  @java.lang.Override
  public vn.trinhtung.grpc.ShowResponseOrBuilder getShowOrBuilder() {
    return getShow();
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
    if (show_ != null) {
      output.writeMessage(1, getShow());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (show_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getShow());
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
    if (!(obj instanceof vn.trinhtung.grpc.GetShowByIdResponse)) {
      return super.equals(obj);
    }
    vn.trinhtung.grpc.GetShowByIdResponse other = (vn.trinhtung.grpc.GetShowByIdResponse) obj;

    if (hasShow() != other.hasShow()) return false;
    if (hasShow()) {
      if (!getShow()
          .equals(other.getShow())) return false;
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
    if (hasShow()) {
      hash = (37 * hash) + SHOW_FIELD_NUMBER;
      hash = (53 * hash) + getShow().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static vn.trinhtung.grpc.GetShowByIdResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.trinhtung.grpc.GetShowByIdResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.trinhtung.grpc.GetShowByIdResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.trinhtung.grpc.GetShowByIdResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.trinhtung.grpc.GetShowByIdResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.trinhtung.grpc.GetShowByIdResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.trinhtung.grpc.GetShowByIdResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static vn.trinhtung.grpc.GetShowByIdResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static vn.trinhtung.grpc.GetShowByIdResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static vn.trinhtung.grpc.GetShowByIdResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static vn.trinhtung.grpc.GetShowByIdResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static vn.trinhtung.grpc.GetShowByIdResponse parseFrom(
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
  public static Builder newBuilder(vn.trinhtung.grpc.GetShowByIdResponse prototype) {
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
   * Protobuf type {@code vn.trinhtung.grpc.GetShowByIdResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:vn.trinhtung.grpc.GetShowByIdResponse)
      vn.trinhtung.grpc.GetShowByIdResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return vn.trinhtung.grpc.Show.internal_static_vn_trinhtung_grpc_GetShowByIdResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return vn.trinhtung.grpc.Show.internal_static_vn_trinhtung_grpc_GetShowByIdResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              vn.trinhtung.grpc.GetShowByIdResponse.class, vn.trinhtung.grpc.GetShowByIdResponse.Builder.class);
    }

    // Construct using vn.trinhtung.grpc.GetShowByIdResponse.newBuilder()
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
      if (showBuilder_ == null) {
        show_ = null;
      } else {
        show_ = null;
        showBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return vn.trinhtung.grpc.Show.internal_static_vn_trinhtung_grpc_GetShowByIdResponse_descriptor;
    }

    @java.lang.Override
    public vn.trinhtung.grpc.GetShowByIdResponse getDefaultInstanceForType() {
      return vn.trinhtung.grpc.GetShowByIdResponse.getDefaultInstance();
    }

    @java.lang.Override
    public vn.trinhtung.grpc.GetShowByIdResponse build() {
      vn.trinhtung.grpc.GetShowByIdResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public vn.trinhtung.grpc.GetShowByIdResponse buildPartial() {
      vn.trinhtung.grpc.GetShowByIdResponse result = new vn.trinhtung.grpc.GetShowByIdResponse(this);
      if (showBuilder_ == null) {
        result.show_ = show_;
      } else {
        result.show_ = showBuilder_.build();
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
      if (other instanceof vn.trinhtung.grpc.GetShowByIdResponse) {
        return mergeFrom((vn.trinhtung.grpc.GetShowByIdResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(vn.trinhtung.grpc.GetShowByIdResponse other) {
      if (other == vn.trinhtung.grpc.GetShowByIdResponse.getDefaultInstance()) return this;
      if (other.hasShow()) {
        mergeShow(other.getShow());
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
      vn.trinhtung.grpc.GetShowByIdResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (vn.trinhtung.grpc.GetShowByIdResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private vn.trinhtung.grpc.ShowResponse show_;
    private com.google.protobuf.SingleFieldBuilderV3<
        vn.trinhtung.grpc.ShowResponse, vn.trinhtung.grpc.ShowResponse.Builder, vn.trinhtung.grpc.ShowResponseOrBuilder> showBuilder_;
    /**
     * <code>.vn.trinhtung.grpc.ShowResponse show = 1;</code>
     * @return Whether the show field is set.
     */
    public boolean hasShow() {
      return showBuilder_ != null || show_ != null;
    }
    /**
     * <code>.vn.trinhtung.grpc.ShowResponse show = 1;</code>
     * @return The show.
     */
    public vn.trinhtung.grpc.ShowResponse getShow() {
      if (showBuilder_ == null) {
        return show_ == null ? vn.trinhtung.grpc.ShowResponse.getDefaultInstance() : show_;
      } else {
        return showBuilder_.getMessage();
      }
    }
    /**
     * <code>.vn.trinhtung.grpc.ShowResponse show = 1;</code>
     */
    public Builder setShow(vn.trinhtung.grpc.ShowResponse value) {
      if (showBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        show_ = value;
        onChanged();
      } else {
        showBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.vn.trinhtung.grpc.ShowResponse show = 1;</code>
     */
    public Builder setShow(
        vn.trinhtung.grpc.ShowResponse.Builder builderForValue) {
      if (showBuilder_ == null) {
        show_ = builderForValue.build();
        onChanged();
      } else {
        showBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.vn.trinhtung.grpc.ShowResponse show = 1;</code>
     */
    public Builder mergeShow(vn.trinhtung.grpc.ShowResponse value) {
      if (showBuilder_ == null) {
        if (show_ != null) {
          show_ =
            vn.trinhtung.grpc.ShowResponse.newBuilder(show_).mergeFrom(value).buildPartial();
        } else {
          show_ = value;
        }
        onChanged();
      } else {
        showBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.vn.trinhtung.grpc.ShowResponse show = 1;</code>
     */
    public Builder clearShow() {
      if (showBuilder_ == null) {
        show_ = null;
        onChanged();
      } else {
        show_ = null;
        showBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.vn.trinhtung.grpc.ShowResponse show = 1;</code>
     */
    public vn.trinhtung.grpc.ShowResponse.Builder getShowBuilder() {
      
      onChanged();
      return getShowFieldBuilder().getBuilder();
    }
    /**
     * <code>.vn.trinhtung.grpc.ShowResponse show = 1;</code>
     */
    public vn.trinhtung.grpc.ShowResponseOrBuilder getShowOrBuilder() {
      if (showBuilder_ != null) {
        return showBuilder_.getMessageOrBuilder();
      } else {
        return show_ == null ?
            vn.trinhtung.grpc.ShowResponse.getDefaultInstance() : show_;
      }
    }
    /**
     * <code>.vn.trinhtung.grpc.ShowResponse show = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        vn.trinhtung.grpc.ShowResponse, vn.trinhtung.grpc.ShowResponse.Builder, vn.trinhtung.grpc.ShowResponseOrBuilder> 
        getShowFieldBuilder() {
      if (showBuilder_ == null) {
        showBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            vn.trinhtung.grpc.ShowResponse, vn.trinhtung.grpc.ShowResponse.Builder, vn.trinhtung.grpc.ShowResponseOrBuilder>(
                getShow(),
                getParentForChildren(),
                isClean());
        show_ = null;
      }
      return showBuilder_;
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


    // @@protoc_insertion_point(builder_scope:vn.trinhtung.grpc.GetShowByIdResponse)
  }

  // @@protoc_insertion_point(class_scope:vn.trinhtung.grpc.GetShowByIdResponse)
  private static final vn.trinhtung.grpc.GetShowByIdResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new vn.trinhtung.grpc.GetShowByIdResponse();
  }

  public static vn.trinhtung.grpc.GetShowByIdResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetShowByIdResponse>
      PARSER = new com.google.protobuf.AbstractParser<GetShowByIdResponse>() {
    @java.lang.Override
    public GetShowByIdResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetShowByIdResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetShowByIdResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetShowByIdResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public vn.trinhtung.grpc.GetShowByIdResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

