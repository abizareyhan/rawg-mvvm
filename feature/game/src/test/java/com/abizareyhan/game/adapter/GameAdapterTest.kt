package com.abizareyhan.game.adapter

import com.abizareyhan.game.model.Game
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.Month

class GameAdapterTest {
    @Test
    fun `itemCount for endlessScrollEnabled true`() {
        val gameAdapter = GameAdapter(
            endlesssScrollEnabled = true
        ).also {
            val games = (0..9).map {
                Game(
                    id = 1000,
                    name = "Marvel's Spider-Man: Miles Morales",
                    released = LocalDate.of(2022, Month.NOVEMBER, 12),
                    tba = false,
                    backgroundImage = "https://insomniac.games/miles.jpg",
                    rating = 5.0,
                    totalPlayed = 103,
                    description = "Best Spiderman Game Ever",
                    developers = listOf(
                        "Insomniac Games"
                    )
                )
            }

            it.items.addAll(games)
        }

        assertEquals(11, gameAdapter.itemCount)
    }

    @Test
    fun `itemCount for endlessScrollEnabled false`() {
        val gameAdapter = GameAdapter(
            endlesssScrollEnabled = false
        ).also {
            val games = (0..9).map {
                Game(
                    id = 1000,
                    name = "Marvel's Spider-Man: Miles Morales",
                    released = LocalDate.of(2022, Month.NOVEMBER, 12),
                    tba = false,
                    backgroundImage = "https://insomniac.games/miles.jpg",
                    rating = 5.0,
                    totalPlayed = 103,
                    description = "Best Spiderman Game Ever",
                    developers = listOf(
                        "Insomniac Games"
                    )
                )
            }

            it.items.addAll(games)
        }

        assertEquals(10, gameAdapter.itemCount)
    }

    @Test
    fun `getItemViewType lastItem for endlessScrollEnabled true`() {
        val gameAdapter = GameAdapter(
            endlesssScrollEnabled = true
        ).also {
            val games = (0..9).map {
                Game(
                    id = 1000,
                    name = "Marvel's Spider-Man: Miles Morales",
                    released = LocalDate.of(2022, Month.NOVEMBER, 12),
                    tba = false,
                    backgroundImage = "https://insomniac.games/miles.jpg",
                    rating = 5.0,
                    totalPlayed = 103,
                    description = "Best Spiderman Game Ever",
                    developers = listOf(
                        "Insomniac Games"
                    )
                )
            }

            it.items.addAll(games)
        }

        assertEquals(0, gameAdapter.getItemViewType(9))
        assertEquals(1, gameAdapter.getItemViewType(10))
    }

    @Test
    fun `getItemViewType lastItem for endlessScrollEnabled false`() {
        val gameAdapter = GameAdapter(
            endlesssScrollEnabled = false
        ).also {
            val games = (0..9).map {
                Game(
                    id = 1000,
                    name = "Marvel's Spider-Man: Miles Morales",
                    released = LocalDate.of(2022, Month.NOVEMBER, 12),
                    tba = false,
                    backgroundImage = "https://insomniac.games/miles.jpg",
                    rating = 5.0,
                    totalPlayed = 103,
                    description = "Best Spiderman Game Ever",
                    developers = listOf(
                        "Insomniac Games"
                    )
                )
            }

            it.items.addAll(games)
        }

        assertEquals(0, gameAdapter.getItemViewType(9))
    }
}