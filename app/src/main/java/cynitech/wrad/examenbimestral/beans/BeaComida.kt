package cynitech.wrad.examenbimestral.beans

import android.os.Parcel
import android.os.Parcelable

class BeaComida(val id: Int,
                val nombrePlato: String,
                val descripcionPlato: String,
                val nacionalidad: String,
                val numeroPersonas: Int,
                val picante: Boolean,
                val ingredientes: List<BeaIngrediente>
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.createTypedArrayList(BeaIngrediente)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombrePlato)
        parcel.writeString(descripcionPlato)
        parcel.writeString(nacionalidad)
        parcel.writeInt(numeroPersonas)
        parcel.writeByte(if (picante) 1 else 0)
        parcel.writeTypedList(ingredientes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BeaComida> {
        override fun createFromParcel(parcel: Parcel): BeaComida {
            return BeaComida(parcel)
        }

        override fun newArray(size: Int): Array<BeaComida?> {
            return arrayOfNulls(size)
        }
    }

}