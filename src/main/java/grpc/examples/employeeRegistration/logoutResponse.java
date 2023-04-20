// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: employeeRegistration.proto

package grpc.examples.employeeRegistration;

/**
 * Protobuf type {@code strings.logoutResponse}
 */
public  final class logoutResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:strings.logoutResponse)
    logoutResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use logoutResponse.newBuilder() to construct.
  private logoutResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private logoutResponse() {
    confirmMessage_ = "";
    detail_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private logoutResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
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
            java.lang.String s = input.readStringRequireUtf8();

            confirmMessage_ = s;
            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            detail_ = rawValue;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
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
    return grpc.examples.employeeRegistration.EmployeeRegistrationServiceImpl.internal_static_strings_logoutResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return grpc.examples.employeeRegistration.EmployeeRegistrationServiceImpl.internal_static_strings_logoutResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            grpc.examples.employeeRegistration.logoutResponse.class, grpc.examples.employeeRegistration.logoutResponse.Builder.class);
  }

  /**
   * Protobuf enum {@code strings.logoutResponse.Enum}
   */
  public enum Enum
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>Admin = 0;</code>
     */
    Admin(0),
    /**
     * <code>Manager = 1;</code>
     */
    Manager(1),
    /**
     * <code>Employee = 2;</code>
     */
    Employee(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>Admin = 0;</code>
     */
    public static final int Admin_VALUE = 0;
    /**
     * <code>Manager = 1;</code>
     */
    public static final int Manager_VALUE = 1;
    /**
     * <code>Employee = 2;</code>
     */
    public static final int Employee_VALUE = 2;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static Enum valueOf(int value) {
      return forNumber(value);
    }

    public static Enum forNumber(int value) {
      switch (value) {
        case 0: return Admin;
        case 1: return Manager;
        case 2: return Employee;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<Enum>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        Enum> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Enum>() {
            public Enum findValueByNumber(int number) {
              return Enum.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return grpc.examples.employeeRegistration.logoutResponse.getDescriptor().getEnumTypes().get(0);
    }

    private static final Enum[] VALUES = values();

    public static Enum valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private Enum(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:strings.logoutResponse.Enum)
  }

  public static final int CONFIRMMESSAGE_FIELD_NUMBER = 1;
  private volatile java.lang.Object confirmMessage_;
  /**
   * <code>string confirmMessage = 1;</code>
   */
  public java.lang.String getConfirmMessage() {
    java.lang.Object ref = confirmMessage_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      confirmMessage_ = s;
      return s;
    }
  }
  /**
   * <code>string confirmMessage = 1;</code>
   */
  public com.google.protobuf.ByteString
      getConfirmMessageBytes() {
    java.lang.Object ref = confirmMessage_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      confirmMessage_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DETAIL_FIELD_NUMBER = 2;
  private int detail_;
  /**
   * <code>.strings.logoutResponse.Enum detail = 2;</code>
   */
  public int getDetailValue() {
    return detail_;
  }
  /**
   * <code>.strings.logoutResponse.Enum detail = 2;</code>
   */
  public grpc.examples.employeeRegistration.logoutResponse.Enum getDetail() {
    @SuppressWarnings("deprecation")
    grpc.examples.employeeRegistration.logoutResponse.Enum result = grpc.examples.employeeRegistration.logoutResponse.Enum.valueOf(detail_);
    return result == null ? grpc.examples.employeeRegistration.logoutResponse.Enum.UNRECOGNIZED : result;
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
    if (!getConfirmMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, confirmMessage_);
    }
    if (detail_ != grpc.examples.employeeRegistration.logoutResponse.Enum.Admin.getNumber()) {
      output.writeEnum(2, detail_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getConfirmMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, confirmMessage_);
    }
    if (detail_ != grpc.examples.employeeRegistration.logoutResponse.Enum.Admin.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, detail_);
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
    if (!(obj instanceof grpc.examples.employeeRegistration.logoutResponse)) {
      return super.equals(obj);
    }
    grpc.examples.employeeRegistration.logoutResponse other = (grpc.examples.employeeRegistration.logoutResponse) obj;

    boolean result = true;
    result = result && getConfirmMessage()
        .equals(other.getConfirmMessage());
    result = result && detail_ == other.detail_;
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + CONFIRMMESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getConfirmMessage().hashCode();
    hash = (37 * hash) + DETAIL_FIELD_NUMBER;
    hash = (53 * hash) + detail_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static grpc.examples.employeeRegistration.logoutResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.examples.employeeRegistration.logoutResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.examples.employeeRegistration.logoutResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.examples.employeeRegistration.logoutResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.examples.employeeRegistration.logoutResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.examples.employeeRegistration.logoutResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.examples.employeeRegistration.logoutResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static grpc.examples.employeeRegistration.logoutResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static grpc.examples.employeeRegistration.logoutResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static grpc.examples.employeeRegistration.logoutResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static grpc.examples.employeeRegistration.logoutResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static grpc.examples.employeeRegistration.logoutResponse parseFrom(
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
  public static Builder newBuilder(grpc.examples.employeeRegistration.logoutResponse prototype) {
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
   * Protobuf type {@code strings.logoutResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:strings.logoutResponse)
      grpc.examples.employeeRegistration.logoutResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return grpc.examples.employeeRegistration.EmployeeRegistrationServiceImpl.internal_static_strings_logoutResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return grpc.examples.employeeRegistration.EmployeeRegistrationServiceImpl.internal_static_strings_logoutResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              grpc.examples.employeeRegistration.logoutResponse.class, grpc.examples.employeeRegistration.logoutResponse.Builder.class);
    }

    // Construct using grpc.examples.employeeRegistration.logoutResponse.newBuilder()
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
      confirmMessage_ = "";

      detail_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return grpc.examples.employeeRegistration.EmployeeRegistrationServiceImpl.internal_static_strings_logoutResponse_descriptor;
    }

    @java.lang.Override
    public grpc.examples.employeeRegistration.logoutResponse getDefaultInstanceForType() {
      return grpc.examples.employeeRegistration.logoutResponse.getDefaultInstance();
    }

    @java.lang.Override
    public grpc.examples.employeeRegistration.logoutResponse build() {
      grpc.examples.employeeRegistration.logoutResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public grpc.examples.employeeRegistration.logoutResponse buildPartial() {
      grpc.examples.employeeRegistration.logoutResponse result = new grpc.examples.employeeRegistration.logoutResponse(this);
      result.confirmMessage_ = confirmMessage_;
      result.detail_ = detail_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof grpc.examples.employeeRegistration.logoutResponse) {
        return mergeFrom((grpc.examples.employeeRegistration.logoutResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(grpc.examples.employeeRegistration.logoutResponse other) {
      if (other == grpc.examples.employeeRegistration.logoutResponse.getDefaultInstance()) return this;
      if (!other.getConfirmMessage().isEmpty()) {
        confirmMessage_ = other.confirmMessage_;
        onChanged();
      }
      if (other.detail_ != 0) {
        setDetailValue(other.getDetailValue());
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
      grpc.examples.employeeRegistration.logoutResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (grpc.examples.employeeRegistration.logoutResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object confirmMessage_ = "";
    /**
     * <code>string confirmMessage = 1;</code>
     */
    public java.lang.String getConfirmMessage() {
      java.lang.Object ref = confirmMessage_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        confirmMessage_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string confirmMessage = 1;</code>
     */
    public com.google.protobuf.ByteString
        getConfirmMessageBytes() {
      java.lang.Object ref = confirmMessage_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        confirmMessage_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string confirmMessage = 1;</code>
     */
    public Builder setConfirmMessage(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      confirmMessage_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string confirmMessage = 1;</code>
     */
    public Builder clearConfirmMessage() {
      
      confirmMessage_ = getDefaultInstance().getConfirmMessage();
      onChanged();
      return this;
    }
    /**
     * <code>string confirmMessage = 1;</code>
     */
    public Builder setConfirmMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      confirmMessage_ = value;
      onChanged();
      return this;
    }

    private int detail_ = 0;
    /**
     * <code>.strings.logoutResponse.Enum detail = 2;</code>
     */
    public int getDetailValue() {
      return detail_;
    }
    /**
     * <code>.strings.logoutResponse.Enum detail = 2;</code>
     */
    public Builder setDetailValue(int value) {
      detail_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.strings.logoutResponse.Enum detail = 2;</code>
     */
    public grpc.examples.employeeRegistration.logoutResponse.Enum getDetail() {
      @SuppressWarnings("deprecation")
      grpc.examples.employeeRegistration.logoutResponse.Enum result = grpc.examples.employeeRegistration.logoutResponse.Enum.valueOf(detail_);
      return result == null ? grpc.examples.employeeRegistration.logoutResponse.Enum.UNRECOGNIZED : result;
    }
    /**
     * <code>.strings.logoutResponse.Enum detail = 2;</code>
     */
    public Builder setDetail(grpc.examples.employeeRegistration.logoutResponse.Enum value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      detail_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.strings.logoutResponse.Enum detail = 2;</code>
     */
    public Builder clearDetail() {
      
      detail_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:strings.logoutResponse)
  }

  // @@protoc_insertion_point(class_scope:strings.logoutResponse)
  private static final grpc.examples.employeeRegistration.logoutResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new grpc.examples.employeeRegistration.logoutResponse();
  }

  public static grpc.examples.employeeRegistration.logoutResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<logoutResponse>
      PARSER = new com.google.protobuf.AbstractParser<logoutResponse>() {
    @java.lang.Override
    public logoutResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new logoutResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<logoutResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<logoutResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public grpc.examples.employeeRegistration.logoutResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
