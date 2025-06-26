package com.alenga.fixmyhood.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alenga.fixmyhood.presentation.viewmodel.EducationViewModel

@Composable
fun EducationScreen(
    viewModel: EducationViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val challenges by viewModel.challenges.collectAsState()
    val education by viewModel.challenges.collectAsState()
    val resources = listOf(
        "🌿 How to Start Composting",
        "♻️ Recycling Guide for Beginners",
        "🚰 Save Water: 10 Easy Tips",
        "🌍 The Impact of Fast Fashion",
        "📺 Watch: The Story of Plastic",
        "Understanding Our Environment: A Comprehensive Guide\n" +
                "The environment encompasses all living and non-living things occurring naturally on Earth, including air, water, soil, plants, animals, and ecosystems. It provides the resources necessary for life and sustains human activities. However, human actions are significantly impacting the environment, leading to challenges like pollution, climate change, and biodiversity loss. This composition explores the environment's components, current challenges, and actionable solutions, enriched with visual aids to deepen understanding.\n" +
                "1. Components of the Environment\n" +
                "The environment consists of several interconnected components:\n" +
                "\n" +
                "Atmosphere: The layer of gases surrounding Earth, essential for climate regulation and life support.\n" +
                "Hydrosphere: All water bodies, including oceans, rivers, lakes, and groundwater.\n" +
                "Lithosphere: The Earth's crust, including soil and minerals, which supports plant growth and human infrastructure.\n" +
                "Biosphere: The regions of Earth where life exists, including ecosystems like forests, grasslands, and wetlands.\n" +
                "\n" +
                "Image 1: Insert an infographic illustrating the Earth's atmosphere, hydrosphere, lithosphere, and biosphere, showing their interconnections (e.g., a diagram with labeled sections of air, water, land, and ecosystems).\n" +
                "Why It Matters\n" +
                "Each component interacts to maintain Earth's balance. For example, forests (biosphere) absorb carbon dioxide from the atmosphere, while oceans (hydrosphere) regulate global temperatures. Disruptions in one component can affect the entire system.\n" +
                "2. Major Environmental Challenges\n" +
                "Human activities have led to significant environmental issues. Below are the primary challenges:\n" +
                "2.1 Climate Change\n" +
                "Climate change, driven by greenhouse gas emissions from burning fossil fuels, deforestation, and industrial activities, causes rising global temperatures, extreme weather, and sea-level rise.\n" +
                "\n" +
                "Impact: Melting polar ice caps, more frequent hurricanes, and disrupted ecosystems.\n" +
                "Example: The 2023 global average temperature was about 1.2°C above pre-industrial levels, according to NASA.\n" +
                "\n" +
                "Image 2: Insert a graph showing global temperature rise from 1900 to 2023, highlighting the sharp increase in recent decades.\n" +
                "2.2 Pollution\n" +
                "Pollution contaminates air, and soil, harming human health and wildlife.\n" +
                "\n" +
                "Air Pollution: Caused by vehicle emissions, industrial processes, and burning fossil fuels. Particulate matter (PM2.5) can lead to respiratory diseases.\n" +
                "Water Pollution: Plastic waste, oil spills, and chemical runoff degrade aquatic ecosystems.\n" +
                "Soil Pollution: Pesticides and industrial waste reduce soil fertility.\n" +
                "\n" +
                "Image 3: Insert a photo of a polluted river with visible plastic waste and debris, contrasted with a clean river to highlight the difference.\n" +
                "2.3 Deforestation\n" +
                "Deforestation, the clearing of forests for agriculture or urban development, destroys habitats and reduces carbon sequestration.\n" +
                "\n" +
                "Statistic: Approximately 10 million hectares of forest are lost annually, according to the FAO.\n" +
                "Impact: Loss of biodiversity and increased CO2 levels.\n" +
                "\n" +
                "Image 4: Insert a before-and-after satellite image of a deforested area, showing lush forest vs. barren land.\n" +
                "2.4 Biodiversity Loss\n" +
                "Biodiversity loss occurs when species disappear due to habitat destruction, pollution, or climate change.\n" +
                "\n" +
                "Example: The IUCN Red List reports over 40,000 species are threatened with extinction.\n" +
                "Consequence: Disrupted ecosystems, affecting pollination, food chains, and ecosystem services.\n" +
                "\n" +
                "Image 5: Insert an image of a diverse coral reef alongside a bleached coral reef to show the impact of biodiversity loss.\n" +
                "3. Solutions to Environmental Challenges\n" +
                "Addressing environmental issues requires collective action. Here are key solutions:\n" +
                "3.1 Renewable Energy\n" +
                "Transitioning to solar, wind, and hydropower reduces greenhouse gas emissions.\n" +
                "\n" +
                "Example: In 2024, renewable energy accounted for 30% of global electricity, per the IEA.\n" +
                "Action: Support policies promoting clean energy and invest in solar panels for homes.\n" +
                "\n" +
                "Image 6: Insert an image of a solar farm or wind turbines in a scenic landscape.\n" +
                "3.2 Sustainable Practices\n" +
                "Adopting sustainable agriculture, reducing waste, and recycling can minimize environmental impact.\n" +
                "\n" +
                "Tips:\n" +
                "Use reusable bags and bottles.\n" +
                "Compost organic waste.\n" +
                "Support local, sustainable food sources.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Image 7: Insert a photo of a community recycling center or a farmer’s market with sustainable produce.\n" +
                "3.3 Conservation Efforts\n" +
                "Protecting natural habitats and endangered species is critical.\n" +
                "\n" +
                "Example: Reforestation projects, like the Great Green Wall in Africa, aim to restore degraded land.\n" +
                "Action: Participate in tree-planting initiatives or support conservation organizations.\n" +
                "\n" +
                "Image 8: Insert an image of a reforestation project with volunteers planting trees.\n" +
                "3.4 Policy and Advocacy\n" +
                "Strong environmental policies and public awareness can drive change.\n" +
                "\n" +
                "Example: The Paris Agreement aims to limit global warming to 1.5°C.\n" +
                "Action: Vote for leaders prioritizing environmental policies and educate others about sustainability.\n" +
                "\n" +
                "Image 9: Insert an image of a climate change protest or a signed international environmental agreement.\n" +
                "4. How You Can Help\n" +
                "Every individual can contribute to a healthier environment:\n" +
                "\n" +
                "Reduce Energy Use: Turn off lights, unplug devices, and use energy-efficient appliances.\n" +
                "Conserve Water: Fix leaks, use water-saving fixtures, and avoid overuse.\n" +
                "Support Green Initiatives: Donate to or volunteer with environmental NGOs.\n" +
                "Educate Others: Share knowledge about environmental issues and solutions.\n" +
                "\n" +
                "Image 10: Insert an inspiring image of a community cleanup event with people collecting trash in a park.\n" +
                "Conclusion\n" +
                "The environment is a complex, interconnected system that sustains life but faces significant threats from human activities. By understanding its components, recognizing challenges like climate change and pollution, and taking action through sustainable practices and advocacy, we can protect our planet for future generations. Visualizing these issues and solutions through images reinforces their urgency and inspires action.\n" +
                "Call to Action: Start small—reduce, reuse, recycle, and stay informed. Together, we can create a sustainable future."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "📚 Education Hub",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            // Resources Section
            item {
                Text(
                    text = "Resources",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            items(resources) { resource ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { /* Add navigation or action here if needed */ },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = resource,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            // Challenges Section
            item {
                Text(
                    text = "Challenges",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            items(education) { challenge ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { viewModel.toggleChallengeCompletion(challenge) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = challenge.isCompleted,
                            onCheckedChange = { viewModel.toggleChallengeCompletion(challenge) }
                        )
                        Text(
                            text = challenge.title,
                            modifier = Modifier.padding(start = 8.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}
