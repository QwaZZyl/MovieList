package com.karavatskiy.serhii.requestmovieslist.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Serhii on 31.03.2019.
 */
class FilterYear(val criteria: String, override val isTitle: Boolean = false) :
    FilterItem,
    Comparable<FilterYear>,
    Parcelable {

    private var isSelected: Boolean = false
    private var enabled: Boolean = true

    fun isSelected(): Boolean {
        return isSelected
    }

    fun setSelected(selected: Boolean) {
        this.isSelected = selected
    }

    fun isEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    override fun compareTo(other: FilterYear): Int = criteria.compareTo(other.criteria)

    constructor(source: Parcel) : this(
        source.readString(),
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(criteria)
        writeInt((if (isTitle) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<FilterYear> = object : Parcelable.Creator<FilterYear> {
            override fun createFromParcel(source: Parcel): FilterYear = FilterYear(source)
            override fun newArray(size: Int): Array<FilterYear?> = arrayOfNulls(size)
        }
    }
}

class FilterGenre(val criteria: String, override val isTitle: Boolean = false) :
    FilterItem,
    Comparable<FilterGenre>,
    Parcelable {

    private var isSelected: Boolean = false

    fun isSelected(): Boolean {
        return isSelected
    }

    fun setSelected(selected: Boolean) {
        this.isSelected = selected
    }

    override fun compareTo(other: FilterGenre): Int = criteria.compareTo(other.criteria)

    constructor(source: Parcel) : this(
        source.readString(),
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(criteria)
        writeInt((if (isTitle) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<FilterGenre> = object : Parcelable.Creator<FilterGenre> {
            override fun createFromParcel(source: Parcel): FilterGenre = FilterGenre(source)
            override fun newArray(size: Int): Array<FilterGenre?> = arrayOfNulls(size)
        }
    }
}

class FilterDirector(
    val criteria: String,
    override val isTitle: Boolean = false
) : FilterItem, Comparable<FilterDirector>, Parcelable {

    private var isSelected: Boolean = false

    fun isSelected(): Boolean {
        return isSelected
    }

    fun setSelected(selected: Boolean) {
        this.isSelected = selected
    }

    override fun compareTo(other: FilterDirector): Int = criteria.compareTo(other.criteria)

    constructor(source: Parcel) : this(
        source.readString(),
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(criteria)
        writeInt((if (isTitle) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<FilterDirector> = object : Parcelable.Creator<FilterDirector> {
            override fun createFromParcel(source: Parcel): FilterDirector = FilterDirector(source)
            override fun newArray(size: Int): Array<FilterDirector?> = arrayOfNulls(size)
        }
    }
}

class Title(val body: String, override val isTitle: Boolean = true) : FilterItem, Comparable<Title>, Parcelable {
    override fun compareTo(other: Title): Int = body.compareTo(other.body)

    constructor(source: Parcel) : this(
        source.readString(),
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(body)
        writeInt((if (isTitle) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Title> = object : Parcelable.Creator<Title> {
            override fun createFromParcel(source: Parcel): Title = Title(source)
            override fun newArray(size: Int): Array<Title?> = arrayOfNulls(size)
        }
    }
}
