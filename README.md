<p align="middle">
    <img alt="AppVeyor" src="https://img.shields.io/appveyor/build/oppahansi/javalings">
    <img alt="GitHub" src="https://img.shields.io/github/license/oppahansi/javalings">
    <img alt="GitHub issues" src="https://img.shields.io/github/issues/oppahansi/javalings">
    <img alt="GitHub pull requests" src="https://img.shields.io/github/issues-pr/oppahansi/javalings">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/oppahansi/javalings">
</p>

# javalings ☕ 💻  (in development)

--- 

Greetings and welcome to `javalings`.

This project contains small exercises to get you used to reading and writing Java code.  
This includes reading and responding to compiler messages!  

In case you have not noticed it yet:  
This project was inspired by [rustlings](https://github.com/rust-lang/rustlings).

  
javalings screenshots:
<p align="middle">
  <img src="https://i.imgur.com/9txdJDJ.png" width="32%" />
  <img src="https://i.imgur.com/YTPAPpF.png" width="32%" /> 
  <img src="https://i.imgur.com/misxcE2.png" width="32%" />
</p>
<p align="middle">
  <img src="https://i.imgur.com/SgHUOJB.png" width="32%" />
  <img src="https://i.imgur.com/3KA5BNR.png" width="32%" /> 
</p>

---  

## Installing javalings (auto)

<details>
  <summary>Windwos</summary>

Open PowerShell (Run as Administrator). Navigate to the location where javalings should be installed.
Then set `ExecutionPolicy` to `RemoteSigned`:

```ps1
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

Then, you can run:

```ps1
Start-BitsTransfer -Source https://raw.githubusercontent.com/oppahansi/javalings/main/install_javalings.ps1 -Destination $env:TMP/install_javalings.ps1; Unblock-File $env:TMP/install_javalings.ps1; Invoke-Expression $env:TMP/install_javalings.ps1
```

This will install the required dependencies and clone the repository in the current folder.  
Once everything is done, navigate inside the `javalings` folder.  

```ps1
cd javalings
```

If you get a permission denied message, you might have to exclude the directory where you cloned javalings in your antivirus.

</details>

<details>
  <summary>MacOs / Linux(Ubuntu) (NOT WORKING YET)</summary>

Navigate to the location where javalings should be installed. Then run:

```shell
curl -L https://raw.githubusercontent.com/oppahansi/javalings/main/install_javalings.sh | bash
```

Or if you want to pass in the path instead:

```shell
curl -L https://raw.githubusercontent.com/rust-lang/rustlings/main/install_javalings.sh | bash -s mypath/
```

This will install the required dependencies and clone the repository in the current folder.  
Once everything is done, navigate inside the `javalings` folder.

</details>

---

## Installing javalings (manually)

<details>
  <summary>Windows</summary>

### Java

Either use the installer from the oracle:  
[Oracle Installer](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

Or use a package manager:

chocolatey:
```ps1
choco install openjdk --version=17.0.2
```

winget:
```ps1
winget install -e --id ojdkbuild.openjdk.17.jdk
```

### Git

Follow the [official Git installation guides](https://git-scm.com/downloads)

### Source Code

**Either clone the repository:**  
Windows / MacOS / Linux(Ubuntu), in your preferred shell/console:

SSH
```shell
git clone git@github.com:oppahansi/javalings.git
```
HTTPS
```shell
git clone https://github.com/oppahansi/javalings.git
```

**OR download the source code and extract it.**

**Note:**  
If you choose to download the source code the `reset` command will not work.  
`reset` command needs repository cloning.

### CMD/PowerShell color codes fix for Windows 10

I am using ANSI characters to display colors and special characters.
To correctly display those in CMD or PowerShell you will have to enable   
VirtualTerminalLevel.

Run this in powershell, not sure if admin is needed. Rerun as admin if it fails.

```ps1
Set-ItemProperty HKCU:\Console VirtualTerminalLevel -Type DWORD 1
```

</details>

<details>
  <summary>MacOs</summary>

### Java

Either use the official
Oracle [DMG installer](https://www.oracle.com/java/technologies/downloads/#java17)

or the brew package manager:

```shell
brew install openjdk@17
```

### Git

Follow the [official Git installation guides](https://git-scm.com/downloads)

### Source Code

**Either clone the repository:**  
Windows / MacOS / Linux(Ubuntu), in your preferred shell/console:

SSH
```shell
git clone git@github.com:oppahansi/javalings.git
```
HTTPS
```shell
git clone https://github.com/oppahansi/javalings.git
```

**OR download the source code and extract it.**

**Note:**  
If you choose to download the source code the `reset` command will not work.  
`reset` command needs repository cloning.

</details>

<details>
  <summary>Linux(Ubuntu)</summary>

### Java

```shell
sudo apt update
sudo apt install openjdk-17-jdk
```

### Git

Follow the [official Git installation guides](https://git-scm.com/downloads)

### Source Code

**Either clone the repository:**  
Windows / MacOS / Linux(Ubuntu), in your preferred shell/console:

SSH
```shell
git clone git@github.com:oppahansi/javalings.git
```
HTTPS
```shell
git clone https://github.com/oppahansi/javalings.git
```

**OR download the source code and extract it.**

**Note:**  
If you choose to download the source code the `reset` command will not work.  
`reset` command needs repository cloning.

</details>

---  

## Usage

### Javalings Command

<details>
  <summary>Windows</summary>

All commands begin with `.\javalings.bat`

</details>

<details>
  <summary>Linux/MacOS</summary>

It might be needed to execute `chmod +x javalings.sh` and `chmod +x gradlew` before you can run javalings.  
All commands begin with `./javalings.sh`

</details>

### Javalings Command Start Options

`javalings` is a placeholder, replace it depending on your OS:

* `.\javalings.bat` on Windows
* `./javalings.sh` on Linux/MacOs

**Tip:**  
Create an alias in your shell for the above script files.

<br>

<details>
<summary>Options</summary>

```
javalings help

    * Print all available options.  
```
```
javalings watch

    * Run exercises in the recommended order.  
```
```
javalings list

    * List all unsolved exercises.  
```
```
javalings run <option>

* Options:
    * exercise
        * Run the specified exercise.
    * next
        * Run the next unsolved exercise.
    * Example:
        * javalings run Intro1  
```
```
javalings reset <option>

* Options:
    * exercise
        * Reset the specified exercise.
    * all
        * Reset all exercises to its unsolved state.
        * CAUTION, this cannot be undone.
    * Example:
        * javalings reset Intro1  
        
DOES NOT WORK IF YOU DOWNLOAD THE SOURCE CODE.
RESET COMMAND NEEDS REPOSITORY CLONING.
```

</details>

### Javalings Watch Mode Options

Commands available to you during the watch mode.

<details>
<summary>Options</summary>

```
help

    * Print all available watch mode options.  
```
```
hint

    * Prints a hint for the current exercise.  
```
```
google

    * Prints examples of what to google to find a solution.  
```
```
next

    * Run the next unsolved exercise. 
      Works only when the current exercise has been solved.  
```
```
reset

    * Resets the current exercise.  
    
DOES NOT WORK IF YOU DOWNLOAD THE SOURCE CODE.
RESET COMMAND NEEDS REPOSITORY CLONING.
```
```
clear

    * Clears the screen.  
```
```
quit or exit

    * Stops the exercise and quits javalings.    
```

</details>

---

## Contributing

See [CONTRIBUTING.md](./CONTRIBUTING.md).

---

# TODO
### javalings commands
* [x] help
* [x] watch (without automatically jump to next exercise)
* [x] list
* [x] run
* [x] reset

### watch mode commands
* [x] help
* [x] hint
* [x] google
* [x] next
* [x] reset
* [x] clear
* [x] quit or exit

### installer
* [x] Windows
* [ ] MacOs / Linux

### exercises (will need more time and effort)
* [x] intros (first version, still unsure)
* [ ] exams
* [x] variables (first version, still unsure)
* [x] primitive datatypes (first version, still unsure)
* [ ] complex datatypes
* [ ] ... more coming soon

### Others
* [ ] add allcontributors bot
* [ ] rethink exercise order and structure
* [ ] watch mode optimisation? Better implementation?
* [ ] gitpod / codespaces ?
* [ ] ... more to be added
