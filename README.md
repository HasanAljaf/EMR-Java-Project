# Simplified EMR System

A console-based Electronic Medical Record (EMR) application written in plain Java for an Object-Oriented Programming semester project. It manages patients, doctors, appointments, and basic medical records through a text menu, and saves data to files between runs.

> **Note:** This is a student project. It uses fictitious data only and is not suitable for clinical use.

## Project Status

🚧 In development. Features are being built in small, tested increments (see [Development Plan](#development-plan)).

## Features

- Add and view patients and doctors
- Find a patient or doctor by ID
- Schedule appointments between a patient and a doctor
- Prevent a doctor from being double-booked at the same date and time
- View a patient's appointments and a doctor's schedule
- Cancel or complete an appointment
- Add medical records to a patient and view their medical history
- Save data to files and reload it on the next run
- Validate input and handle errors without crashing

## OOP Concepts Demonstrated

| Concept | Where it appears |
|---|---|
| Abstraction | Abstract `Person` class; `EMRSystem` and `FileManager` hide internal details |
| Inheritance | `Patient` and `Doctor` extend `Person` |
| Polymorphism | `Patient` and `Doctor` override the same `Person` display method |
| Encapsulation | Private fields, controlled state changes, no direct access to internal lists |
| Composition | A `Patient` owns its list of `MedicalRecord` objects |
| Association | An `Appointment` connects an existing `Patient` and `Doctor` |
| Exceptions | Custom exceptions for unknown IDs, scheduling conflicts, and invalid data |
| Collections | `ArrayList` storage for patients, doctors, appointments, and records |

## Project Structure

```
src/
└── emr/
    ├── model/         Person, Patient, Doctor, Appointment, AppointmentStatus, MedicalRecord
    ├── application/   EMRSystem
    ├── ui/            MenuManager
    ├── storage/       FileManager
    ├── exception/     RecordNotFoundException, AppointmentConflictException, InvalidDataException
    └── EMRApplication.java
```

## Requirements

- Java Development Kit (JDK) 17 or later
- IntelliJ IDEA (recommended) or any Java IDE

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/<repo-name>.git
   ```
2. Open the project folder in IntelliJ IDEA.
3. Make sure a JDK is selected under **File → Project Structure → Project**.
4. Run `EMRApplication.java`.

## Data Files

_To be documented once file persistence is implemented._

## Development Plan

- [ ] 1. Project and package setup
- [ ] 2. `Person`, `Patient`, and `Doctor`
- [ ] 3. `AppointmentStatus` and `Appointment`
- [ ] 4. `MedicalRecord`
- [ ] 5. Patient and doctor management in `EMRSystem`
- [ ] 6. Appointment management and conflict detection
- [ ] 7. Console menus and input validation
- [ ] 8. File persistence
- [ ] 9. Testing, cleanup, and documentation

## Scope and Limitations

This is the class version of the project. It intentionally excludes databases, web frameworks, REST APIs, graphical interfaces, authentication, billing, and multi-user access. It stores data in local text files and runs entirely in the console.

## Author

Hasan — Object-Oriented Programming course project, 2026.
