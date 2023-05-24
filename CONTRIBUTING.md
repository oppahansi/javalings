## Contributing to Javalings

First off, thanks for taking the time to contribute!! 🔥️

### Quick Reference

I want to...

_add an exercise! ➡️ [read this](#addex) and then [open a Pull Request](#prs)_

_update an outdated exercise! ➡️ [open a Pull Request](#prs)_

_report a bug! ➡️ [open an Issue](#issues)_

_fix a bug! ➡️ [open a Pull Request](#prs)_

_implement a new feature! ➡️ [open an Issue to discuss it first, then a Pull Request](#issues)_

<a name="#src"></a>
### Working on the source code

* Make sure you have the dependencies installed [Installation steps](https://github.com/oppahansi/javalings#installing-javalings-auto)
* Make sure you have at least Java 17, project default is Java 17
    * Next LTS: Java 21 in September 2023
* This is gradle project
  * Either open the folder in your IDE
  * OR import it as gradle project
  * It should use the included gradle wrapper
    * if it doesn't, make sure you have gradle 8.1+ installed
* The main module is `javalings`
* All exercises are in the submodule `exercises`
  * This module is excluded from compilation / build process
  * That is necessary to avoid all the compilation errors
* Make sure you are using the google code style
  * [intellij-java-google-style.xml](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml) for importing
  * [eclipse-java-google-style.xml](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml) for importing
  * Then set hard wrap at to 120 columns

That's it. You should be ready to go.  
In case you have difficulties open a blank issue or contact me on discord:  
`oppa#4496`

<a name="addex"></a>
### Adding an exercise

The first step is to add the exercise!   
The source code goes into the `exercises/src/main/java` folder. Take a look inside.  
The `package name` signifies the subject of the exercise. The `exercise name` is the subject followed by a number.  

Example:  
`intros/Intro3.java`

Next you have to add the exercise info.  
You will find all current exercise info inside the javalings `src/main/resources/exercises.json` file.

Exercise info template:
```json
{
  "name": "primitives6",
  "packageName": "datatypes.primitives",
  "path": "exercises/src/main/java/datatypes/primitives/Primitives6.java",
  "hint": "Some primitive data types require an explicit number literal when defining a value.",
  "google": "  java primitives\n  java creating variables\n  java double\n  java number literals\n  java floating point operations"
}
```

The order of the exercise info inside the `exercises.json` file is the recommended order.  
Insert your exercise at a position where you think it would make most sense for a beginner.   

In case you are unsure where to put it, just choose a spot, add it and then mention it in the  
pull request.  We will find a solution together.

At this point you should be able to start javalings with your exercise by running:  
``.\javalings.bat run yourExerciseName``
or
``./javalings.sh run yourExerciseName``

### Debugging / Running inside the IDE

When running or debugging inside the ide you will have to create a start configuration and  
provide the commands as arguments.

For example, if you want to run / debug `Intro3` exercise, then the arguments would be:  
`run intro3`


<a name="issues"></a>
### Issues

You can open an issue [here](https://github.com/oppahansi/javalings/issues/new/choose).
Choose a template, fill it out and then submit.

<a name="prs"></a>
### Pull Requests

First fork the repository.  
Add your changes. Make sure everything works, maybe add a test.
Commit the changes.

Opening a pull request is as easy as forking the repository and committing your
changes. There's a couple of things to watch out for:

#### Write correct commit messages

We follow the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0-beta.4/)
specification.
This means that you have to format your commit messages in a specific way. Say
you're working on adding a new exercise called `Foobar1.java`. You could write
the following commit message:

```
feat: add Foobar1.java exercise
```

If you're just fixing a bug, please use the `fix` type:

```
fix(list): make sure list doesn't self-destruct
```

The scope within the brackets is optional, but should be any of these:

- `installation` (for the installation script)
- `cli` (for general CLI changes)
- ``cmd`` (for command changes)
- `watch` (for the watch functionality source)
- `run` (for the run functionality source)
- `EXERCISENAME` (if you're changing a specific exercise, or set of exercises,
  substitute them here)

When the commit also happens to close an existing issue, link it in the message
body:

```
fix: update foobar

closes #1234567
```

If you're doing simple changes, like updating a link in README.md, use `chore`:

```
chore: update XYZ link in README
```

If you're updating documentation, use `docs`:

```
docs: add more information to Readme
```

If, and only if, you're absolutely sure you want to make a breaking change
(please discuss this beforehand!), add an exclamation mark to the type and
explain the breaking change in the message body:

```
fix!: completely change cli

BREAKING CHANGE: This has to be done because lorem ipsum dolor
```

#### Pull Request Workflow

Once you open a Pull Request, it may be reviewed or labeled (or both) until
the maintainers accept your change. Please be patient, it may take some time
for this to happen! We are all doing this in our free time.