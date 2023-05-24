package com.example.aston_intensiv_6.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Contact(
    var id: Int,
    var phoneNumber: String,
    var firstName: String,
    var lastName: String,
    var imageUrl: String,
    var isImDeleteVisible: Boolean
) :
    Parcelable
