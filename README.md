# Secure Banking Application

## Description
A console-based Java banking application developed as a mini project for ISS 2101
(Secure Coding). The system allows users to register, log in, and manage a simple bank
account — including depositing funds, withdrawing funds, and viewing their balance —
with secure coding principles applied throughout the design and implementation.

## Student Details
- **Name:** Rebecca Chiwenga
- **Registration Number:** H250685R
- **Course:** ISS 2101 – Secure Coding
- **Level:** Bachelor of Technology (Hons), Part 2
- **Institution:** Harare Institute of Technology

## Features
- User registration and login with duplicate-username prevention
- Secure password storage using salted SHA-256 hashing (no plaintext passwords stored)
- Account creation, balance viewing, deposits, and withdrawals
- Input validation on all monetary transactions (rejects negative amounts, non-numeric
  input, and withdrawals exceeding the available balance)
- Transaction history logging with timestamps
- File-based data persistence (users, accounts, and transactions saved between sessions)
- Generic, user-facing error messages with no internal system details exposed
  (secure error handling)

## Security Measures Implemented
- **Encapsulation:** All class fields are private, accessed only through controlled
  methods, preventing direct/unauthorized manipulation of account data.
- **Secure password storage:** Passwords are never stored in plaintext; each is hashed
  with a unique random salt using SHA-256.
- **Input validation:** Deposit and withdrawal amounts are validated before processing;
  invalid input is rejected rather than causing unexpected behaviour.
- **Fail-secure error handling:** Exceptions are caught and translated into generic
  messages for the user, while avoiding exposure of stack traces or internal system
  details.
- **Least privilege in data access:** Each user can only view and modify their own
  account information.

## How to Run
1. Open the project in IntelliJ IDEA.
2. Run `Main.java`.
3. Follow the on-screen menu to register, log in, and manage your account.