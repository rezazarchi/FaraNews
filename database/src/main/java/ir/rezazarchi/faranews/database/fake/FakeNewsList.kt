package ir.rezazarchi.faranews.database.fake

import ir.rezazarchi.faranews.database.entity.NewsEntity
import java.time.Instant

object FakeNewsList {
    val newsList = listOf(
        NewsEntity(
            id = 1,
            source = "Tech Daily",
            author = "John Doe",
            title = "Microsoft Unveils Revolutionary AI Technology",
            description = "Microsoft has announced a groundbreaking AI that redefines human interaction.",
            url = "https://www.techdaily.com/microsoft-ai",
            urlToImage = "https://www.techdaily.com/images/microsoft-ai.jpg",
            publishedAt = Instant.parse("2025-04-06T09:00:00Z"),
            content = "Microsoft has revealed its most advanced AI technology yet, promising new ways for humans to connect and collaborate.",
            query = "Microsoft"
        ),
        NewsEntity(
            id = 2,
            source = "Apple Insider",
            author = "Jane Smith",
            title = "Apple Launches Next-Gen iPhone with Holographic Display",
            description = "The new iPhone brings cutting-edge holographic technology to the masses.",
            url = "https://www.appleinsider.com/nextgen-iphone",
            urlToImage = "https://www.appleinsider.com/images/nextgen-iphone.jpg",
            publishedAt = Instant.parse("2025-04-06T11:30:00Z"),
            content = "Apple’s latest iPhone is here, and it’s packed with innovative features including a holographic display.",
            query = "Apple"
        ),
        NewsEntity(
            id = 3,
            source = "Tech Crunch",
            author = "Alice Green",
            title = "Google Maps Adds Real-Time Translation Feature",
            description = "Google Maps can now translate signs and menus in real time using AR.",
            url = "https://www.techcrunch.com/google-maps-translation",
            urlToImage = "https://www.techcrunch.com/images/google-maps.jpg",
            publishedAt = Instant.parse("2025-04-06T12:15:00Z"),
            content = "Google Maps’ new update enables users to translate foreign text using augmented reality directly in the app.",
            query = "Google"
        ),
        NewsEntity(
            id = 4,
            source = "Tesla World",
            author = "Mark Thompson",
            title = "Tesla Reveals New Solar-Powered Cybertruck",
            description = "The Cybertruck now comes with integrated solar panels for increased efficiency.",
            url = "https://www.teslaworld.com/solar-cybertruck",
            urlToImage = "https://www.teslaworld.com/images/solar-cybertruck.jpg",
            publishedAt = Instant.parse("2025-04-06T14:00:00Z"),
            content = "Tesla has launched a new version of its Cybertruck, equipped with solar panels to extend its range significantly.",
            query = "Tesla"
        )
    )

}