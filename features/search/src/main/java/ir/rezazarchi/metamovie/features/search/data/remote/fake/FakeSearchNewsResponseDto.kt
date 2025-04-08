package ir.rezazarchi.metamovie.features.search.data.remote.fake

import ir.rezazarchi.metamovie.features.search.data.remote.dto.ArticleDto
import ir.rezazarchi.metamovie.features.search.data.remote.dto.NewsResponseDto
import ir.rezazarchi.metamovie.features.search.data.remote.dto.SourceDto

object FakeSearchNewsResponseDto {
    val fakeSearchDto = NewsResponseDto(
        status = "ok",
        totalResults = 100,
        articles = listOf(
            ArticleDto(
                source = SourceDto(
                    id = null,
                    name = "Tech Daily"
                ),
                author = "John Doe",
                title = "Microsoft Unveils Revolutionary AI Technology",
                description = "Microsoft has announced a groundbreaking AI that redefines human interaction.",
                url = "https://www.techdaily.com/microsoft-ai",
                urlToImage = "https://www.techdaily.com/images/microsoft-ai.jpg",
                publishedAt = "2025-04-06T21:08:28Z",
                content = "Microsoft has revealed its most advanced AI technology yet, promising new ways for humans to connect and collaborate.",
            ),
            ArticleDto(
                source = SourceDto(
                    id = null,
                    name = "Apple Insider"
                ),
                author = "Jane Smith",
                title = "Apple Launches Next-Gen iPhone with Holographic Display",
                description = "The new iPhone brings cutting-edge holographic technology to the masses.",
                url = "https://www.appleinsider.com/nextgen-iphone",
                urlToImage = "https://www.appleinsider.com/images/nextgen-iphone.jpg",
                publishedAt = "2025-04-07T09:15:02Z",
                content = "Apple’s latest iPhone is here, and it’s packed with innovative features including a holographic display.",
            ),
            ArticleDto(
                source = SourceDto(
                    id = null,
                    name = "Tech Crunch"
                ),
                author = "Alice Green",
                title = "Google Maps Adds Real-Time Translation Feature",
                description = "Google Maps can now translate signs and menus in real time using AR.",
                url = "https://www.techcrunch.com/google-maps-translation",
                urlToImage = "https://www.techcrunch.com/images/google-maps.jpg",
                publishedAt = "2025-04-07T10:41:07Z",
                content = "Google Maps’ new update enables users to translate foreign text using augmented reality directly in the app.",
            ),
        )
    )
}