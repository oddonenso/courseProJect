/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package android.hardware.tv.tuner;
/** @hide */
public class FrontendIsdbs3Settings implements android.os.Parcelable
{
  public long frequency = 0L;
  public long endFrequency = 0L;
  public int streamId = 0;
  public int streamIdType = android.hardware.tv.tuner.FrontendIsdbsStreamIdType.UNDEFINED;
  public int modulation = android.hardware.tv.tuner.FrontendIsdbs3Modulation.UNDEFINED;
  public int coderate = android.hardware.tv.tuner.FrontendIsdbs3Coderate.UNDEFINED;
  public int symbolRate = 0;
  public int rolloff = android.hardware.tv.tuner.FrontendIsdbs3Rolloff.UNDEFINED;
  @Override
   public final int getStability() { return android.os.Parcelable.PARCELABLE_STABILITY_VINTF; }
  public static final android.os.Parcelable.Creator<FrontendIsdbs3Settings> CREATOR = new android.os.Parcelable.Creator<FrontendIsdbs3Settings>() {
    @Override
    public FrontendIsdbs3Settings createFromParcel(android.os.Parcel _aidl_source) {
      FrontendIsdbs3Settings _aidl_out = new FrontendIsdbs3Settings();
      _aidl_out.readFromParcel(_aidl_source);
      return _aidl_out;
    }
    @Override
    public FrontendIsdbs3Settings[] newArray(int _aidl_size) {
      return new FrontendIsdbs3Settings[_aidl_size];
    }
  };
  @Override public final void writeToParcel(android.os.Parcel _aidl_parcel, int _aidl_flag)
  {
    int _aidl_start_pos = _aidl_parcel.dataPosition();
    _aidl_parcel.writeInt(0);
    _aidl_parcel.writeLong(frequency);
    _aidl_parcel.writeLong(endFrequency);
    _aidl_parcel.writeInt(streamId);
    _aidl_parcel.writeInt(streamIdType);
    _aidl_parcel.writeInt(modulation);
    _aidl_parcel.writeInt(coderate);
    _aidl_parcel.writeInt(symbolRate);
    _aidl_parcel.writeInt(rolloff);
    int _aidl_end_pos = _aidl_parcel.dataPosition();
    _aidl_parcel.setDataPosition(_aidl_start_pos);
    _aidl_parcel.writeInt(_aidl_end_pos - _aidl_start_pos);
    _aidl_parcel.setDataPosition(_aidl_end_pos);
  }
  public final void readFromParcel(android.os.Parcel _aidl_parcel)
  {
    int _aidl_start_pos = _aidl_parcel.dataPosition();
    int _aidl_parcelable_size = _aidl_parcel.readInt();
    try {
      if (_aidl_parcelable_size < 4) throw new android.os.BadParcelableException("Parcelable too small");;
      if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) return;
      frequency = _aidl_parcel.readLong();
      if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) return;
      endFrequency = _aidl_parcel.readLong();
      if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) return;
      streamId = _aidl_parcel.readInt();
      if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) return;
      streamIdType = _aidl_parcel.readInt();
      if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) return;
      modulation = _aidl_parcel.readInt();
      if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) return;
      coderate = _aidl_parcel.readInt();
      if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) return;
      symbolRate = _aidl_parcel.readInt();
      if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) return;
      rolloff = _aidl_parcel.readInt();
    } finally {
      if (_aidl_start_pos > (Integer.MAX_VALUE - _aidl_parcelable_size)) {
        throw new android.os.BadParcelableException("Overflow in the size of parcelable");
      }
      _aidl_parcel.setDataPosition(_aidl_start_pos + _aidl_parcelable_size);
    }
  }
  @Override
  public int describeContents() {
    int _mask = 0;
    return _mask;
  }
}
