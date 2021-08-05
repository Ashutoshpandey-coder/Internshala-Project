package com.example.internshala.models

import android.os.Parcel
import android.os.Parcelable

data class WorkshopModel(
    val id : Int = 0,
    val title : String = "",
    val description : String = "",
    val mode : String = "",
    val image : String = "",
    var isApplied : Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun describeContents(): Int {
       return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        dest.writeString(title)
        dest.writeString(description)
        dest.writeInt(id)
        dest.writeString(mode)
        dest.writeString(image)
        dest.writeInt(isApplied)
    }

    companion object CREATOR : Parcelable.Creator<WorkshopModel> {
        override fun createFromParcel(parcel: Parcel): WorkshopModel {
            return WorkshopModel(parcel)
        }

        override fun newArray(size: Int): Array<WorkshopModel?> {
            return arrayOfNulls(size)
        }
    }
}