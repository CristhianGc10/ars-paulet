# Ars Paulet 🎌

**Japonés para la vida cotidiana** - Aplicación de aprendizaje basada en Irodori

## 📋 Descripción

Ars Paulet es una aplicación premium de aprendizaje de japonés diseñada con filosofía Wabi-Sabi.
Incluye 54 lecciones distribuidas en 3 niveles: Inicial, Básico 1 y Básico 2.

Cada lección contiene 4 actividades:
- 🎧 **Audición** (聴解) - Comprensión auditiva
- 💬 **Conversación** (会話) - Práctica de diálogos
- 📖 **Lectura** (読解) - Comprensión de textos
- ✍️ **Escritura** (作文) - Práctica de escritura

## 🛠️ Tecnologías

| Componente | Versión | Descripción |
|------------|---------|-------------|
| Kotlin | 2.1.0 | Lenguaje de programación |
| Jetpack Compose | 1.7.6 | Framework UI moderno |
| Material 3 | 1.3.1 | Design system |
| Navigation Compose | 2.8.5 | Navegación |
| ExoPlayer (Media3) | 1.5.1 | Reproductor de audio |
| Coil | 3.0.4 | Carga de imágenes |
| Lottie | 6.6.2 | Animaciones |
| Accompanist | 0.37.0 | Complementos Compose |
| Room | 2.6.1 | Base de datos local |
| Coroutines | 1.9.0 | Programación asíncrona |

## 🏗️ Arquitectura

```
com.ars.paulet/
├── domain/           # Capa de dominio (modelos, repositorios, casos de uso)
│   ├── model/
│   ├── repository/
│   └── usecase/
├── data/             # Capa de datos (implementaciones, Room, servicios)
│   ├── local/
│   ├── repository/
│   ├── service/
│   └── mapper/
└── presentation/     # Capa de presentación (UI, ViewModels)
    ├── navigation/
    ├── theme/
    ├── components/
    └── screens/
```

## 🎨 Tema Wabi-Sabi

La paleta de colores está inspirada en la estética japonesa Wabi-Sabi:
- **Colores primarios**: Tonos de madera y tierra
- **Colores neutros**: Papel washi, arena
- **Acentos**: Índigo japonés, terracota, dorado antiguo
- **Tipografía**: Noto Sans JP

## 📱 Requisitos

- **Minimum SDK**: API 36 (Android 16.0)
- **Target SDK**: API 36
- **Gradle**: 8.11.1

## 🚀 Instalación

1. Clona el repositorio o copia los archivos
2. Abre en Android Studio (versión más reciente)
3. Sincroniza Gradle
4. Descarga las fuentes Noto Sans JP y colócalas en `app/src/main/res/font/`
5. Ejecuta en emulador o dispositivo

### Fuentes Noto Sans JP

Descarga de [Google Fonts](https://fonts.google.com/noto/specimen/Noto+Sans+JP) y renombra:
- `NotoSansJP-Thin.ttf` → `noto_sans_jp_thin.ttf`
- `NotoSansJP-Light.ttf` → `noto_sans_jp_light.ttf`
- `NotoSansJP-Regular.ttf` → `noto_sans_jp_regular.ttf`
- `NotoSansJP-Medium.ttf` → `noto_sans_jp_medium.ttf`
- `NotoSansJP-Bold.ttf` → `noto_sans_jp_bold.ttf`
- `NotoSansJP-Black.ttf` → `noto_sans_jp_black.ttf`

## 📁 Estructura de Contenido

```
assets/
├── audio/           # Archivos de audio de lecciones
├── images/          # Imágenes del contenido
└── lottie/          # Animaciones Lottie

Organización de lecciones:
├── inicial/         # 18 lecciones
├── basico1/         # 18 lecciones
└── basico2/         # 18 lecciones
```

## 📝 Próximos Pasos

1. [ ] Agregar fuentes Noto Sans JP
2. [ ] Implementar ViewModels
3. [ ] Conectar repositorios con Room
4. [ ] Cargar contenido de lecciones (JSON/Assets)
5. [ ] Implementar reproductor de audio completo
6. [ ] Agregar animaciones Lottie
7. [ ] Implementar sistema de progreso
8. [ ] Tests unitarios y de UI
9. [ ] Optimización de rendimiento

## 📄 Licencia

Proyecto privado - Todos los derechos reservados

---

**いろどり** - Japonés para la vida cotidiana
