# Facade Design Pattern — Class Diagram

`MediaFacade` sits in front of three independent subsystem classes (`ImageViewer`, `MusicPlayer`, `VideoPlayer`) and exposes one simple call per use case, hiding each subsystem's multi-step sequence.

```mermaid
classDiagram
    direction TB

    class Main {
      <<client>>
      +main() void
    }

    class MediaFacade {
      -ImageViewer imageViewer
      -MusicPlayer musicPlayer
      -VideoPlayer videoPlayer
      +MediaFacade(imageViewer, videoPlayer, musicPlayer)
      +playMusic() void
      +viewImage() void
      +playVideo() void
    }

    class ImageViewer {
      +loadImageFile() void
      +applyScaling() void
      +displayImage() void
    }

    class MusicPlayer {
      +InitializeAudioDriver() void
      +decodeAudio() void
      +startPlayback() void
    }

    class VideoPlayer {
      +setupRenderingEngine() void
      +loadVideoFile() void
      +playVideo() void
    }

    Main ..> MediaFacade : uses
    MediaFacade o--> ImageViewer : delegates
    MediaFacade o--> MusicPlayer : delegates
    MediaFacade o--> VideoPlayer : delegates
```

## Legend

| Arrow | Meaning |
|---|---|
| `..>` | dependency — client only ever calls the facade |
| `o-->` | aggregation — facade holds each subsystem (injected via constructor) |

Parent-first layout note: not applicable here (no inheritance/realization edges) — the facade is placed above the subsystems it delegates to so the diagram reads client → facade → subsystems, top to bottom.

## Scaling: many instances

A single `MediaFacade` field per subsystem works for one session. When many concurrent sessions each need their own wired-up subsystem set, move to a registry keyed by session/context id instead of one shared facade instance.

```mermaid
classDiagram
    direction TB

    class Client {
      +main() void
    }

    class MediaFacadeRegistry {
      -Map~String, MediaFacade~ facadesBySessionId
      +getFacade(sessionId) MediaFacade
    }

    class MediaFacade {
      -ImageViewer imageViewer
      -MusicPlayer musicPlayer
      -VideoPlayer videoPlayer
      +playMusic() void
      +viewImage() void
      +playVideo() void
    }

    class ImageViewer
    class MusicPlayer
    class VideoPlayer

    Client ..> MediaFacadeRegistry : looks up facade by sessionId
    MediaFacadeRegistry o--> MediaFacade : creates & caches per session
    MediaFacade o--> ImageViewer : delegates
    MediaFacade o--> MusicPlayer : delegates
    MediaFacade o--> VideoPlayer : delegates
```
