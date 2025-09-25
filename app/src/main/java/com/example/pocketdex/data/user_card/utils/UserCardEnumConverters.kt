package com.example.pocketdex.data.user_card.enums

// Rarity Enum Converters
fun String.toRarity(): Rarity =
    Rarity.values().firstOrNull() { it.rarityText == this } ?: Rarity.ONE_DIA
fun Rarity.toDbValue(): String = this.rarityText


// Expansion Enum Converters
fun String.toExpansion(): Expansion =
    Expansion.values().firstOrNull() { it.expansionId == this } ?: Expansion.GENETIC_APEX
fun Expansion.toDbValue(): String = this.expansionId


// Pack Enum Converters
fun String.toPack(): Pack =
    Pack.values().firstOrNull() { it.packId == this } ?: Pack.PIKACHU_PACK
fun Pack.toDbValue(): String = this.packId