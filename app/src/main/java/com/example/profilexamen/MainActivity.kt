package com.example.profilexamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.profilexamen.ui.theme.ProfilExamenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilExamenTheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Écran principal
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ProfileCard(
            name = "Julien Hédreul",
            role = "Développeur Angular/React",
            description = "Étudiant en développement informatique, je pratique le Typescript avec Angular",
            skills = listOf(
                "Kotlin",
                "Jetpack Compose",
                "Java",
                "JS",
                "Angular",
                "Symfony",
                "ReactJS",
                "Git"
            ),
            availability = "Disponinible pour une alternance de 2 ans",
            onAvailabilityClick = {
                // pas de fonctionnement
            },
            graduate = "ESGI",
            // padding externe : le parent place la carte dans l'écran
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp)
        )
    }
}

// Fiche de profil
@Composable
fun ProfileCard(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    availability: String,
    onAvailabilityClick: () -> Unit, // vide pour l'instant
    graduate: String,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        Column(
            // padding interne
            modifier = Modifier.padding(16.dp)
        ) {
            ProfileHeader(
                name = name,
                role = role,
                graduate = graduate,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Description",
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = description,
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            SkillsColumn(
                skills = skills,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background,shape = MaterialTheme.shapes.small)
            )
            Spacer(modifier = Modifier.height(12.dp))
            InteractionAvailability(
                availability = availability,
                onClick = onAvailabilityClick,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}

// En-tête du profil
@Composable
fun ProfileHeader(
    name: String,
    role: String,
    graduate: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        tonalElevation = 2.dp
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileImage(
                    profileTitle = name
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Surface(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                shape = MaterialTheme.shapes.small,
                tonalElevation = 6.dp
            ) {
                Text(
                    text = graduate,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }

    Text(
        text = role,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun ProfileImage(
    profileTitle: String,
    modifier: Modifier = Modifier
) {
    androidx.compose.foundation.Image(
        painter = painterResource(id = android.R.drawable.ic_menu_gallery),
        contentDescription = "Image de profil de $profileTitle",
        modifier = modifier
    )
}

@Composable
fun SkillsColumn(skills: List<String>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Compétences",
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(skills) { skill ->
                SkillItem(skill = skill)
            }
        }
    }
}

/*
* skills.forEach { skill ->
                SkillItem(skill = skill)
            }
            *  */
// Une compétence -> équivalent à SkillCard dans le pdf du Devoir
@Composable
fun SkillItem(
    skill: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxHeight(), shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        // pour afficher la compété sous forme de liste en -
        Text(
            text = skill,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

// Zone cliquable de disponibilité (action + accessibilité)
@Composable
fun InteractionAvailability(
    availability: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.LightGray,
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                role = Role.Button,
                onClick = onClick
            )
            .semantics {
                contentDescription = "Contacter : téléphone"
            }
    ) {
        Text(
            text = availability,
            modifier = Modifier.padding(12.dp)
        )
    }
}

// PREVIEWS ------------------
@Preview(showBackground = true)
@Composable
fun ProfileJuniorPreview() {
    ProfilExamenTheme() {
        ProfileCard(
            name = "Jean Tanrien",
            role = "Dev junior",
            description = "Étudiant en BUT Info",
            skills = listOf("Kotlin", "Android Studio", "Java/Spring"),
            availability = "Disponible pour un stage",
            onAvailabilityClick = {},
            graduate = "INSA",
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileFullStackPreview() {
    ProfilExamenTheme() {
        ProfileCard(
            name = "Julien Hédreul",
            role = "Développeur Angular/React",
            description = "Étudiant en développement informatique à l'ESGI, je recherche une alternance de 2ans dans le dev web/applicatif. Auriez-vous des contacts ? Si mon profil vous intéresse, je serai heureux de pouvoir échanger avec vous ;)" +
                    "\nMail: hedrjul@gmail.com (tout est vrai)",
            skills = listOf(
                "Angular",
                "JavaScript",
                "React",
                "MongoDB",
                "Symfony",
                "SQL",
                "Git",
                "PHP",
                "Docker"
            ),
            availability = "Disponible dès maintenant",
            onAvailabilityClick = {},
            graduate = "ESGI",
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        )
    }
}