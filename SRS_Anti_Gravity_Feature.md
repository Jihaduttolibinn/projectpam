# Software Requirements Specification (SRS)
## Anti-Gravity Feature - ProjectPAM

---

## 1. Introduction

### 1.1 Purpose
This Software Requirements Specification (SRS) document provides a comprehensive description of the Anti-Gravity feature to be implemented in the ProjectPAM mobile application. This document is intended for developers, testers, project managers, and stakeholders involved in the development and deployment of this feature.

### 1.2 Scope
The Anti-Gravity feature will enable users to experience a simulated zero-gravity environment within the mobile application. This feature will:
- Provide realistic physics simulation for floating objects
- Allow user interaction with objects in a zero-gravity space
- Enhance user engagement through immersive visual effects
- Utilize device sensors for enhanced interaction

**Product Name**: ProjectPAM - Anti-Gravity Module  
**Platform**: Android (Jetpack Compose)  
**Minimum SDK**: API 24 (Android 7.0)  
**Target SDK**: API 36

### 1.3 Definitions, Acronyms, and Abbreviations
- **SRS**: Software Requirements Specification
- **UI**: User Interface
- **API**: Application Programming Interface
- **SDK**: Software Development Kit
- **FPS**: Frames Per Second
- **PAM**: Pengembangan Aplikasi Mobile (Mobile Application Development)

### 1.4 References
- Android Developer Documentation: https://developer.android.com
- Jetpack Compose Documentation: https://developer.android.com/jetpack/compose
- Material Design 3 Guidelines: https://m3.material.io

### 1.5 Overview
This document is organized into the following sections:
- Section 2: Overall Description - provides context and general factors affecting the product
- Section 3: System Features - details specific features and requirements
- Section 4: External Interface Requirements - describes interfaces with external systems
- Section 5: Nonfunctional Requirements - specifies quality attributes

---

## 2. Overall Description

### 2.1 Product Perspective
The Anti-Gravity feature is a new module to be integrated into the existing ProjectPAM application. It operates as a standalone feature accessible from the main application menu and utilizes the device's hardware capabilities (accelerometer, gyroscope) for enhanced interaction.

### 2.2 Product Functions
The major functions of the Anti-Gravity feature include:
1. **Environment Simulation**: Create and render a zero-gravity environment
2. **Object Management**: Spawn, manage, and render floating objects
3. **Physics Engine**: Simulate realistic zero-gravity physics
4. **User Interaction**: Enable touch and gesture-based object manipulation
5. **Sensor Integration**: Utilize device sensors for tilt-based controls
6. **Settings Management**: Configure anti-gravity parameters

### 2.3 User Classes and Characteristics
**Primary Users**: Mobile application users aged 13+ with basic smartphone operation knowledge
- **Technical Expertise**: Basic to intermediate
- **Frequency of Use**: Casual to regular (entertainment/educational purposes)
- **Device Requirements**: Android smartphone with accelerometer and gyroscope

### 2.4 Operating Environment
- **Platform**: Android OS (API 24 and above)
- **Framework**: Jetpack Compose with Kotlin
- **Hardware**: Smartphone or tablet with:
  - Accelerometer sensor
  - Gyroscope sensor (optional but recommended)
  - Minimum 2GB RAM
  - GPU with OpenGL ES 3.0 support

### 2.5 Design and Implementation Constraints
- Must maintain minimum 30 FPS for smooth animation
- Must be compatible with Jetpack Compose architecture
- Must follow Material Design 3 guidelines
- Must not exceed 50MB additional storage
- Must work offline (no internet connection required)

### 2.6 Assumptions and Dependencies
- Users have granted necessary sensor permissions
- Device has functional accelerometer sensor
- Sufficient device memory available for physics calculations
- Android system provides accurate sensor data

---

## 3. System Features

### 3.1 Anti-Gravity Environment Activation

#### 3.1.1 Description and Priority
The Anti-Gravity Environment Activation feature allows users to toggle between normal and zero-gravity modes within the application. This is the core feature that enables the entire anti-gravity experience.

**Priority**: High

#### 3.1.2 Stimulus/Response Sequences
1. User navigates to the Anti-Gravity section from the main menu
2. System displays the anti-gravity interface with activation toggle
3. User taps the "Activate Anti-Gravity" button
4. System transitions to anti-gravity mode with visual feedback
5. Objects begin floating and responding to zero-gravity physics
6. User can deactivate by tapping the toggle again
7. System smoothly transitions back to normal mode

#### 3.1.3 Functional Requirements

**REQ-AG-1.1**: The system SHALL provide a clearly visible toggle button to activate/deactivate anti-gravity mode.

**REQ-AG-1.2**: The system SHALL display a visual indicator (e.g., icon, color change, animation) when anti-gravity mode is active.

**REQ-AG-1.3**: The system SHALL transition between normal and anti-gravity modes within 500 milliseconds.

**REQ-AG-1.4**: The system SHALL apply anti-gravity physics to all designated objects when activated.

**REQ-AG-1.5**: The system SHALL persist the anti-gravity state during screen rotation.

**REQ-AG-1.6**: The system SHALL provide haptic feedback when toggling anti-gravity mode (if device supports).

---

### 3.2 Physics Simulation Engine

#### 3.2.1 Description and Priority
The Physics Simulation Engine provides realistic zero-gravity physics calculations for all objects in the environment, including floating behavior, momentum, and collision detection.

**Priority**: High

#### 3.2.2 Stimulus/Response Sequences
1. Anti-gravity mode is activated
2. System initializes physics engine with zero-gravity parameters
3. Objects are assigned initial velocities and positions
4. Physics engine continuously calculates object positions based on:
   - Applied forces
   - Momentum
   - Collisions with boundaries
   - User interactions
5. System renders updated positions at 60 FPS

#### 3.2.3 Functional Requirements

**REQ-AG-2.1**: The system SHALL simulate zero-gravity physics with realistic floating behavior.

**REQ-AG-2.2**: The system SHALL maintain object momentum in the absence of external forces.

**REQ-AG-2.3**: The system SHALL detect and respond to collisions between objects.

**REQ-AG-2.4**: The system SHALL implement boundary detection to keep objects within the visible area.

**REQ-AG-2.5**: The system SHALL apply damping forces to prevent infinite acceleration.

**REQ-AG-2.6**: The system SHALL update physics calculations at minimum 30 times per second.

**REQ-AG-2.7**: The system SHALL support at least 20 simultaneous floating objects without performance degradation.

---

### 3.3 Object Interaction and Manipulation

#### 3.3.1 Description and Priority
This feature enables users to interact with floating objects through touch gestures, including pushing, pulling, rotating, and flicking objects in the zero-gravity environment.

**Priority**: High

#### 3.3.2 Stimulus/Response Sequences
1. User touches an object on the screen
2. System highlights the selected object
3. User drags finger across the screen
4. System applies force to the object in the drag direction
5. Object moves according to applied force and physics
6. User releases touch
7. Object continues moving with momentum
8. System gradually reduces velocity due to damping

**Alternative Flow - Rotation**:
1. User performs two-finger rotation gesture on object
2. System applies rotational force
3. Object rotates in zero-gravity space

#### 3.3.3 Functional Requirements

**REQ-AG-3.1**: The system SHALL detect touch input on floating objects.

**REQ-AG-3.2**: The system SHALL provide visual feedback when an object is selected (e.g., highlight, glow effect).

**REQ-AG-3.3**: The system SHALL apply force to objects based on drag gesture velocity and direction.

**REQ-AG-3.4**: The system SHALL support multi-touch interaction for simultaneous object manipulation.

**REQ-AG-3.5**: The system SHALL implement rotation gestures for object rotation.

**REQ-AG-3.6**: The system SHALL provide a "flick" gesture to apply strong impulse forces.

**REQ-AG-3.7**: The system SHALL limit maximum force applied to prevent objects from moving too fast.

**REQ-AG-3.8**: The system SHALL deselect objects when user touches empty space.

---

### 3.4 Sensor-Based Controls

#### 3.4.1 Description and Priority
This feature utilizes the device's accelerometer and gyroscope to enable tilt-based controls, allowing users to influence object movement by physically tilting their device.

**Priority**: Medium

#### 3.4.2 Stimulus/Response Sequences
1. User enables sensor controls in settings
2. System requests and verifies sensor permissions
3. System begins monitoring accelerometer data
4. User tilts device in any direction
5. System applies directional force to all objects based on tilt angle
6. Objects drift in the direction of tilt
7. User returns device to neutral position
8. Objects gradually stop drifting

#### 3.4.3 Functional Requirements

**REQ-AG-4.1**: The system SHALL request accelerometer sensor permissions on first use.

**REQ-AG-4.2**: The system SHALL provide a toggle to enable/disable sensor-based controls.

**REQ-AG-4.3**: The system SHALL read accelerometer data at minimum 30 Hz frequency.

**REQ-AG-4.4**: The system SHALL apply force to objects proportional to device tilt angle.

**REQ-AG-4.5**: The system SHALL calibrate sensor baseline when feature is activated.

**REQ-AG-4.6**: The system SHALL provide sensitivity adjustment settings (low, medium, high).

**REQ-AG-4.7**: The system SHALL gracefully handle sensor unavailability with appropriate user notification.

**REQ-AG-4.8**: The system SHALL limit maximum tilt-based force to prevent excessive object velocity.

---

### 3.5 Object Spawning and Management

#### 3.5.1 Description and Priority
This feature allows users to add, remove, and customize objects in the anti-gravity environment, including different shapes, sizes, and visual properties.

**Priority**: Medium

#### 3.5.2 Stimulus/Response Sequences
1. User taps "Add Object" button
2. System displays object selection menu
3. User selects object type (sphere, cube, custom shape)
4. System spawns object at random position with random velocity
5. Object begins floating in anti-gravity environment
6. User can long-press object to access delete option
7. System removes object with fade-out animation

#### 3.5.3 Functional Requirements

**REQ-AG-5.1**: The system SHALL provide an interface to spawn new objects.

**REQ-AG-5.2**: The system SHALL support at least 5 different object types (sphere, cube, cylinder, cone, custom).

**REQ-AG-5.3**: The system SHALL allow users to customize object color.

**REQ-AG-5.4**: The system SHALL allow users to adjust object size within defined limits.

**REQ-AG-5.5**: The system SHALL spawn objects with random initial velocity and position.

**REQ-AG-5.6**: The system SHALL provide a "Clear All" function to remove all objects.

**REQ-AG-5.7**: The system SHALL limit maximum number of objects to 50 for performance reasons.

**REQ-AG-5.8**: The system SHALL display current object count to the user.

---

### 3.6 Visual Effects and Animation

#### 3.6.1 Description and Priority
This feature provides immersive visual effects including particle systems, glow effects, trails, and smooth animations to enhance the anti-gravity experience.

**Priority**: Low

#### 3.6.2 Stimulus/Response Sequences
1. Anti-gravity mode is activated
2. System displays background particle effects (stars, dust)
3. Objects display glow/aura effects while floating
4. When objects collide, system displays impact particles
5. Objects leave motion trails when moving fast
6. System continuously animates background elements

#### 3.6.3 Functional Requirements

**REQ-AG-6.1**: The system SHALL display background particle effects in anti-gravity mode.

**REQ-AG-6.2**: The system SHALL render smooth animations at minimum 30 FPS.

**REQ-AG-6.3**: The system SHALL provide motion trails for fast-moving objects.

**REQ-AG-6.4**: The system SHALL display visual effects on object collisions.

**REQ-AG-6.5**: The system SHALL allow users to toggle visual effects on/off for performance.

**REQ-AG-6.6**: The system SHALL use GPU acceleration for rendering when available.

---

### 3.7 Settings and Customization

#### 3.7.1 Description and Priority
This feature provides users with settings to customize the anti-gravity experience, including physics parameters, visual preferences, and control options.

**Priority**: Medium

#### 3.7.2 Stimulus/Response Sequences
1. User navigates to Anti-Gravity Settings
2. System displays settings interface
3. User adjusts gravity strength slider
4. System immediately applies new gravity value
5. User observes changes in object behavior
6. User saves settings
7. System persists settings for future sessions

#### 3.7.3 Functional Requirements

**REQ-AG-7.1**: The system SHALL provide settings for gravity strength (0-100% of Earth gravity).

**REQ-AG-7.2**: The system SHALL provide settings for air resistance/damping.

**REQ-AG-7.3**: The system SHALL allow users to toggle collision detection on/off.

**REQ-AG-7.4**: The system SHALL provide performance mode (reduced effects for better FPS).

**REQ-AG-7.5**: The system SHALL persist user settings across app sessions.

**REQ-AG-7.6**: The system SHALL provide a "Reset to Default" option.

**REQ-AG-7.7**: The system SHALL apply setting changes in real-time without requiring restart.

---

## 4. External Interface Requirements

### 4.1 User Interfaces

#### 4.1.1 Main Anti-Gravity Screen
- **Description**: Primary interface for anti-gravity experience
- **Components**:
  - Full-screen canvas for object rendering
  - Floating Action Button (FAB) for adding objects
  - Top app bar with mode indicator and settings icon
  - Toggle button for anti-gravity activation
  - Object counter display
- **Design**: Material Design 3 with dark theme optimized for visual effects

#### 4.1.2 Settings Screen
- **Description**: Configuration interface for anti-gravity parameters
- **Components**:
  - Gravity strength slider (0-100%)
  - Damping/air resistance slider
  - Sensor sensitivity selector (Low/Medium/High)
  - Toggle switches for:
    - Collision detection
    - Visual effects
    - Sensor controls
    - Haptic feedback
  - Performance mode toggle
  - Reset to defaults button
- **Design**: Standard Material Design 3 settings layout

#### 4.1.3 Object Selection Menu
- **Description**: Bottom sheet for selecting object types
- **Components**:
  - Grid of object type icons
  - Color picker
  - Size slider
  - "Add Object" confirmation button
- **Design**: Material Design 3 bottom sheet

#### 4.1.4 UI Mockup Requirements
- All UI elements SHALL follow Material Design 3 guidelines
- Touch targets SHALL be minimum 48dp × 48dp
- Text SHALL be readable with minimum 14sp font size
- Color contrast SHALL meet WCAG 2.1 AA standards
- UI SHALL support both portrait and landscape orientations

### 4.2 Hardware Interfaces

#### 4.2.1 Accelerometer
- **Purpose**: Detect device tilt for sensor-based controls
- **Interface**: Android Sensor API (TYPE_ACCELEROMETER)
- **Sampling Rate**: SENSOR_DELAY_GAME (20ms intervals)
- **Data Format**: 3-axis acceleration values (x, y, z) in m/s²

#### 4.2.2 Gyroscope (Optional)
- **Purpose**: Enhanced rotation detection for advanced controls
- **Interface**: Android Sensor API (TYPE_GYROSCOPE)
- **Sampling Rate**: SENSOR_DELAY_GAME (20ms intervals)
- **Data Format**: 3-axis rotation values (x, y, z) in rad/s

#### 4.2.3 Touchscreen
- **Purpose**: Primary user input for object interaction
- **Interface**: Android MotionEvent API
- **Support**: Multi-touch with minimum 2 simultaneous touch points
- **Gestures**: Tap, drag, flick, pinch, rotate

#### 4.2.4 Vibration Motor
- **Purpose**: Haptic feedback for user actions
- **Interface**: Android Vibrator API
- **Patterns**: Short vibrations (10-50ms) for button presses and collisions

### 4.3 Software Interfaces

#### 4.3.1 Android SDK
- **Component**: Android Framework
- **Version**: API 24 (minimum) to API 36 (target)
- **Purpose**: Core platform services and APIs

#### 4.3.2 Jetpack Compose
- **Component**: UI Toolkit
- **Version**: As defined in project dependencies
- **Purpose**: Declarative UI rendering and state management

#### 4.3.3 Kotlin Coroutines
- **Component**: Concurrency framework
- **Version**: Latest stable
- **Purpose**: Asynchronous physics calculations and sensor data processing

#### 4.3.4 Compose Canvas API
- **Component**: Graphics rendering
- **Purpose**: Custom drawing for objects and effects

### 4.4 Communication Interfaces
- **Not Applicable**: This feature operates entirely offline with no network communication requirements

---

## 5. Nonfunctional Requirements

### 5.1 Performance Requirements

**PERF-1**: The system SHALL maintain minimum 30 FPS during anti-gravity simulation with up to 20 objects.

**PERF-2**: The system SHALL maintain 60 FPS target when visual effects are disabled.

**PERF-3**: The system SHALL respond to touch input within 16ms (one frame at 60 FPS).

**PERF-4**: The system SHALL complete mode transition (normal ↔ anti-gravity) within 500ms.

**PERF-5**: The system SHALL limit CPU usage to maximum 40% on mid-range devices.

**PERF-6**: The system SHALL limit memory usage to maximum 100MB additional RAM.

**PERF-7**: The system SHALL start anti-gravity mode within 1 second of user activation.

**PERF-8**: The system SHALL process sensor data with maximum 50ms latency.

### 5.2 Safety Requirements

**SAFE-1**: The system SHALL display a motion sickness warning on first use of anti-gravity mode.

**SAFE-2**: The system SHALL provide an option to immediately disable all motion and effects if user experiences discomfort.

**SAFE-3**: The system SHALL limit animation speed to prevent inducing motion sickness.

**SAFE-4**: The system SHALL include a "Reduce Motion" accessibility option.

**SAFE-5**: The system SHALL not use flashing effects that could trigger photosensitive epilepsy.

**SAFE-6**: The system SHALL provide clear instructions to avoid using the feature while walking or driving.

### 5.3 Security Requirements

**SEC-1**: The system SHALL request sensor permissions using Android's runtime permission system.

**SEC-2**: The system SHALL handle permission denial gracefully without crashing.

**SEC-3**: The system SHALL not collect or transmit any sensor data externally.

**SEC-4**: The system SHALL store user settings locally using encrypted SharedPreferences.

**SEC-5**: The system SHALL validate all user input to prevent injection attacks.

**SEC-6**: The system SHALL not expose any debugging interfaces in production builds.

### 5.4 Software Quality Attributes

#### 5.4.1 Reliability
- **REL-1**: The system SHALL have a crash-free rate of 99.5% or higher
- **REL-2**: The system SHALL gracefully handle out-of-memory conditions
- **REL-3**: The system SHALL recover from sensor failures without crashing
- **REL-4**: The system SHALL handle rapid screen rotations without data loss

#### 5.4.2 Usability
- **USE-1**: New users SHALL be able to activate anti-gravity mode within 30 seconds
- **USE-2**: The system SHALL provide tooltips for first-time users
- **USE-3**: All interactive elements SHALL provide visual feedback within 100ms
- **USE-4**: Error messages SHALL be clear and actionable
- **USE-5**: The system SHALL support accessibility features (TalkBack, large text)

#### 5.4.3 Maintainability
- **MAINT-1**: Code SHALL follow Kotlin coding conventions
- **MAINT-2**: All public functions SHALL have KDoc documentation
- **MAINT-3**: The system SHALL use dependency injection for testability
- **MAINT-4**: Physics engine SHALL be modular and replaceable
- **MAINT-5**: The system SHALL achieve minimum 70% code coverage with unit tests

#### 5.4.4 Portability
- **PORT-1**: The system SHALL work on devices from API 24 to latest Android version
- **PORT-2**: The system SHALL adapt to different screen sizes (phones, tablets)
- **PORT-3**: The system SHALL support both ARM and x86 architectures
- **PORT-4**: The system SHALL function correctly on devices without gyroscope

#### 5.4.5 Scalability
- **SCALE-1**: The system SHALL support adding new object types without code refactoring
- **SCALE-2**: The physics engine SHALL be extensible for future force types
- **SCALE-3**: The system SHALL support plugin architecture for custom effects

### 5.5 Accessibility Requirements

**ACCESS-1**: The system SHALL support TalkBack screen reader with descriptive labels.

**ACCESS-2**: The system SHALL provide haptic feedback alternatives for visual cues.

**ACCESS-3**: The system SHALL support minimum 200% text scaling.

**ACCESS-4**: The system SHALL meet WCAG 2.1 Level AA contrast requirements.

**ACCESS-5**: The system SHALL provide alternative controls for users unable to use tilt controls.

### 5.6 Localization Requirements

**LOC-1**: All user-facing text SHALL be externalized to string resources.

**LOC-2**: The system SHALL support right-to-left (RTL) layouts.

**LOC-3**: Numbers and units SHALL be formatted according to device locale.

**LOC-4**: The system SHALL support Unicode for international characters.

---

## 6. Other Requirements

### 6.1 Database Requirements
- **DB-1**: User settings SHALL be stored in SharedPreferences
- **DB-2**: Object presets SHALL be stored in local JSON configuration
- **DB-3**: No external database required for this feature

### 6.2 Legal Requirements
- **LEGAL-1**: The system SHALL comply with Google Play Store policies
- **LEGAL-2**: The system SHALL include appropriate content rating information
- **LEGAL-3**: Third-party libraries SHALL use compatible open-source licenses

### 6.3 Reuse Requirements
- **REUSE-1**: Physics engine SHALL be designed as a reusable library
- **REUSE-2**: Sensor handling SHALL use existing Android architecture components
- **REUSE-3**: UI components SHALL follow the app's existing design system

---

## Appendix A: Glossary

| Term | Definition |
|------|------------|
| Anti-Gravity | Simulated environment where objects float without gravitational pull |
| Damping | Gradual reduction of velocity to simulate air resistance |
| FPS | Frames Per Second - measure of animation smoothness |
| Haptic Feedback | Physical vibration response to user actions |
| Impulse Force | Sudden, strong force applied to an object |
| Jetpack Compose | Modern Android UI toolkit for building native interfaces |
| Physics Engine | Software component that simulates physical interactions |
| Zero-Gravity | State where gravitational force is negligible or absent |

---

## Appendix B: Analysis Models

### B.1 Use Case Diagram
```
User
  |
  |-- Activate Anti-Gravity Mode
  |-- Interact with Objects
  |     |-- Touch Object
  |     |-- Drag Object
  |     |-- Rotate Object
  |     |-- Flick Object
  |
  |-- Use Sensor Controls
  |     |-- Tilt Device
  |     |-- Calibrate Sensors
  |
  |-- Manage Objects
  |     |-- Add Object
  |     |-- Remove Object
  |     |-- Customize Object
  |     |-- Clear All Objects
  |
  |-- Configure Settings
        |-- Adjust Gravity
        |-- Adjust Damping
        |-- Toggle Effects
        |-- Set Sensitivity
```

### B.2 State Diagram
```
[Initial State]
     |
     v
[Normal Mode] <---> [Anti-Gravity Mode]
                          |
                          |-- [Idle State]
                          |-- [Object Selected]
                          |-- [Object Moving]
                          |-- [Sensor Active]
```

---

## Appendix C: Issues List

| Issue ID | Description | Priority | Status |
|----------|-------------|----------|--------|
| ISS-001 | Determine optimal physics calculation frequency | High | Open |
| ISS-002 | Define maximum object count for low-end devices | Medium | Open |
| ISS-003 | Specify collision detection algorithm | High | Open |
| ISS-004 | Determine battery impact acceptable threshold | Medium | Open |
| ISS-005 | Define motion sickness warning content | Low | Open |

---

## Document Revision History

| Version | Date | Author | Description |
|---------|------|--------|-------------|
| 1.0 | 2025-12-28 | Development Team | Initial SRS document creation |

---

**Document Status**: Draft  
**Approval Required**: Yes  
**Next Review Date**: TBD

---

*This document is confidential and intended for internal use only.*
