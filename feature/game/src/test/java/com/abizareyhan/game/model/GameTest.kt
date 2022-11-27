package com.abizareyhan.game.model

import com.abizareyhan.domain.model.AddedByStatusModel
import com.abizareyhan.domain.model.GameDetailModel
import com.abizareyhan.domain.model.GameDeveloperModel
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.Month

class GameTest {
    @Test
    fun `create Game object from GameDetailModel using constructor`() {
        val gameDetailModel = GameDetailModel(
            id = 1000,
            name = "Marvel's Spider-Man: Miles Morales",
            released = "2022-11-12",
            tba = false,
            backgroundImage = "https://insomniac.games/miles.jpg",
            rating = 5.0,
            addedByStatus = AddedByStatusModel(
                yet = 101,
                owned = 102,
                beaten = 103,
                toplay = 104,
                dropped = 105,
                playing = 106,
            ),
            description = "Best Spiderman Game Ever",
            developers = listOf(
                GameDeveloperModel(
                    id = 1001,
                    name = "Insomniac Games"
                )
            )
        )

        val game = Game(gameDetailModel)
        val expectedGame = Game(
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

        assertEquals(expectedGame, game)
        assertEquals(expectedGame.id, game.id)
        assertEquals(expectedGame.name, game.name)
        assertEquals(expectedGame.released, game.released)
        assertEquals(expectedGame.released?.year, game.released?.year)
        assertEquals(expectedGame.released?.month, game.released?.month)
        assertEquals(expectedGame.released?.dayOfMonth, game.released?.dayOfMonth)
        assertEquals(expectedGame.backgroundImage, game.backgroundImage)
        assertEquals(expectedGame.rating, game.rating, 0.0)
        assertEquals(expectedGame.totalPlayed, game.totalPlayed)
        assertEquals(expectedGame.description, game.description)
        assertEquals(expectedGame.developers, game.developers)
    }

    @Test
    fun `areItemsTheSame same id`() {
        val firstGame = Game(
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

        val secondGame = Game(
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

        val areItemsTheSame = firstGame.areItemsTheSame(secondGame)

        assertEquals(true, areItemsTheSame)
    }

    @Test
    fun `areItemsTheSame different id`() {
        val firstGame = Game(
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

        val secondGame = Game(
            id = 1001,
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

        val areItemsTheSame = firstGame.areItemsTheSame(secondGame)

        assertEquals(false, areItemsTheSame)
    }

    @Test
    fun `areContentsTheSame all identical`() {
        val firstGame = Game(
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

        val secondGame = Game(
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

        val areContentsTheSame = firstGame.areContentsTheSame(secondGame)

        assertEquals(true, areContentsTheSame)
    }

    @Test
    fun `areContentsTheSame different id`() {
        val firstGame = Game(
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

        val secondGame = Game(
            id = 1001,
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

        val areContentsTheSame = firstGame.areContentsTheSame(secondGame)

        assertEquals(false, areContentsTheSame)
    }

    @Test
    fun `areContentsTheSame different name`() {
        val firstGame = Game(
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

        val secondGame = Game(
            id = 1000,
            name = "Marvel's Spider-Man: Miles Morales (Ultimate Edition)",
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

        val areContentsTheSame = firstGame.areContentsTheSame(secondGame)

        assertEquals(false, areContentsTheSame)
    }

    @Test
    fun `areContentsTheSame different released`() {
        val firstGame = Game(
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

        val secondGame = Game(
            id = 1000,
            name = "Marvel's Spider-Man: Miles Morales",
            released = LocalDate.of(2022, Month.DECEMBER, 12),
            tba = false,
            backgroundImage = "https://insomniac.games/miles.jpg",
            rating = 5.0,
            totalPlayed = 103,
            description = "Best Spiderman Game Ever",
            developers = listOf(
                "Insomniac Games"
            )
        )

        val areContentsTheSame = firstGame.areContentsTheSame(secondGame)

        assertEquals(false, areContentsTheSame)
    }

    @Test
    fun `areContentsTheSame different tba`() {
        val firstGame = Game(
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

        val secondGame = Game(
            id = 1000,
            name = "Marvel's Spider-Man: Miles Morales",
            released = LocalDate.of(2022, Month.NOVEMBER, 12),
            tba = true,
            backgroundImage = "https://insomniac.games/miles.jpg",
            rating = 5.0,
            totalPlayed = 103,
            description = "Best Spiderman Game Ever",
            developers = listOf(
                "Insomniac Games"
            )
        )

        val areContentsTheSame = firstGame.areContentsTheSame(secondGame)

        assertEquals(false, areContentsTheSame)
    }

    @Test
    fun `areContentsTheSame different backgroundImage`() {
        val firstGame = Game(
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

        val secondGame = Game(
            id = 1000,
            name = "Marvel's Spider-Man: Miles Morales",
            released = LocalDate.of(2022, Month.NOVEMBER, 12),
            tba = true,
            backgroundImage = "https://insomniac.games/milesmorales.jpg",
            rating = 5.0,
            totalPlayed = 103,
            description = "Best Spiderman Game Ever",
            developers = listOf(
                "Insomniac Games"
            )
        )

        val areContentsTheSame = firstGame.areContentsTheSame(secondGame)

        assertEquals(false, areContentsTheSame)
    }

    @Test
    fun `areContentsTheSame different rating`() {
        val firstGame = Game(
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

        val secondGame = Game(
            id = 1000,
            name = "Marvel's Spider-Man: Miles Morales",
            released = LocalDate.of(2022, Month.NOVEMBER, 12),
            tba = true,
            backgroundImage = "https://insomniac.games/miles.jpg",
            rating = 4.9,
            totalPlayed = 103,
            description = "Best Spiderman Game Ever",
            developers = listOf(
                "Insomniac Games"
            )
        )

        val areContentsTheSame = firstGame.areContentsTheSame(secondGame)

        assertEquals(false, areContentsTheSame)
    }

    @Test
    fun `areContentsTheSame different totalPlayed`() {
        val firstGame = Game(
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

        val secondGame = Game(
            id = 1000,
            name = "Marvel's Spider-Man: Miles Morales",
            released = LocalDate.of(2022, Month.NOVEMBER, 12),
            tba = true,
            backgroundImage = "https://insomniac.games/miles.jpg",
            rating = 5.0,
            totalPlayed = 104,
            description = "Best Spiderman Game Ever",
            developers = listOf(
                "Insomniac Games"
            )
        )

        val areContentsTheSame = firstGame.areContentsTheSame(secondGame)

        assertEquals(false, areContentsTheSame)
    }

    @Test
    fun `areContentsTheSame different description`() {
        val firstGame = Game(
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

        val secondGame = Game(
            id = 1000,
            name = "Marvel's Spider-Man: Miles Morales",
            released = LocalDate.of(2022, Month.NOVEMBER, 12),
            tba = true,
            backgroundImage = "https://insomniac.games/miles.jpg",
            rating = 5.0,
            totalPlayed = 103,
            description = "Best Spiderman Game Ever!!!!!!!!",
            developers = listOf(
                "Insomniac Games"
            )
        )

        val areContentsTheSame = firstGame.areContentsTheSame(secondGame)

        assertEquals(false, areContentsTheSame)
    }

    @Test
    fun `areContentsTheSame different developers`() {
        val firstGame = Game(
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

        val secondGame = Game(
            id = 1000,
            name = "Marvel's Spider-Man: Miles Morales",
            released = LocalDate.of(2022, Month.NOVEMBER, 12),
            tba = true,
            backgroundImage = "https://insomniac.games/miles.jpg",
            rating = 5.0,
            totalPlayed = 103,
            description = "Best Spiderman Game Ever!!!!!!!!",
            developers = listOf(
                "Nixxes software"
            )
        )

        val areContentsTheSame = firstGame.areContentsTheSame(secondGame)

        assertEquals(false, areContentsTheSame)
    }
}