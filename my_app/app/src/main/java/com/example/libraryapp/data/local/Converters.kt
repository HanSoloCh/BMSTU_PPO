package com.example.libraryapp.data.local

import androidx.room.TypeConverter
import com.example.libraryapp.domain.util.utils.ReservationStatus
import com.example.libraryapp.domain.util.utils.UserRole
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class Converters {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromLocalDate(localDate: LocalDate): String {
        return localDate.format(formatter)
    }

    @TypeConverter
    fun toLocalDate(localDateString: String): LocalDate {
        return LocalDate.parse(localDateString, formatter)
    }

    @TypeConverter
    fun fromUserRole(userRole: UserRole): String {
        return userRole.name
    }

    @TypeConverter
    fun toUserRole(userRoleString: String): UserRole {
        return UserRole.valueOf(userRoleString)
    }

    @TypeConverter
    fun fromReservationStatus(reservationStatus: ReservationStatus): String {
        return reservationStatus.name
    }

    @TypeConverter
    fun toReservationStatus(reservationStatusString: String): ReservationStatus {
        return ReservationStatus.valueOf(reservationStatusString)
    }

    @OptIn(ExperimentalUuidApi::class)
    @TypeConverter
    fun toUuid(uuidString: String): Uuid {
        return Uuid.parse(uuidString)
    }

    @OptIn(ExperimentalUuidApi::class)
    @TypeConverter
    fun fromUuid(uuid: Uuid): String {
        return uuid.toString()
    }
}