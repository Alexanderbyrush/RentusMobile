package com.example.rentusmobile.data.mapper

import com.example.rentusmobile.data.remote.dto.PropertyCreateRequestDto
import com.example.rentusmobile.data.remote.dto.PropertyDto
import com.example.rentusmobile.data.remote.dto.PropertyUpdateRequestDto
import com.example.rentusmobile.domain.model.Property
import java.text.NumberFormat
import java.util.Locale

fun PropertyDto.toDomain(): Property {
    val amount = pricePerMonth ?: price ?: 0.0
    val formatter = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
    return Property(
        id = id,
        title = title.orEmpty().ifBlank { "Sin título" },
        description = description.orEmpty(),
        city = city.orEmpty().ifBlank { "Sin ciudad" },
        location = location.orEmpty().ifBlank { city.orEmpty() },
        price = formatter.format(amount),
        area = "${area ?: 0.0} m²",
        bedrooms = "${bedrooms ?: 0} hab",
        bathrooms = "${bathrooms ?: 0} baños",
        status = status.orEmpty().ifBlank { "desconocido" },
        type = type.orEmpty().ifBlank { "desconocido" },
        viewsCount = viewsCount ?: 0
    )
}

fun Property.toCreateRequest() = PropertyCreateRequestDto(
    title = title,
    description = description,
    city = city,
    location = location,
    price = price.filter { it.isDigit() || it == '.' }.toDoubleOrNull() ?: 0.0,
    bedrooms = bedrooms.filter { it.isDigit() }.toIntOrNull() ?: 0,
    bathrooms = bathrooms.filter { it.isDigit() }.toIntOrNull() ?: 0,
    area = area.filter { it.isDigit() || it == '.' }.toDoubleOrNull() ?: 0.0,
    type = type
)

fun Property.toUpdateRequest() = PropertyUpdateRequestDto(
    title = title,
    description = description,
    city = city,
    location = location,
    price = price.filter { it.isDigit() || it == '.' }.toDoubleOrNull() ?: 0.0,
    bedrooms = bedrooms.filter { it.isDigit() }.toIntOrNull() ?: 0,
    bathrooms = bathrooms.filter { it.isDigit() }.toIntOrNull() ?: 0,
    area = area.filter { it.isDigit() || it == '.' }.toDoubleOrNull() ?: 0.0,
    type = type
)
