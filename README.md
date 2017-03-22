# R&#949;​KiT-Sample-Mod

[![GitHub license](https://img.shields.io/badge/license-GPLv3-blue.svg?style=square)](https://github.com/fuchss-dominik/rekit-game/blob/master/LICENSE.md)

R&#949;​KiT is a platform jumper game entirely written in Java with [AWT](https://docs.oracle.com/javase/8/docs/api/java/awt/package-summary.html).
This project shall help to create addons for R&#949;KiT.

# Steps to create your own mod
## Step 1:
Add R&#949;KiT to your dependencies via Maven, Gradle or just download the zip and add to the Build-Path.

E.g. using Maven:

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
<dependencies>
  <dependency>
    <groupId>com.github.fuchss-dominik.rekit-game</groupId>
    <artifactId>logic</artifactId>
    <version>0.5.2-beta</version>
    <scope>provided</scope>
  </dependency>
</dependencies>
```

## Step 2:
* Add your classes into any subpackage of `rekit` (this is necessary as only the subpackages of `rekit` will be traversed by the search routine).
* Mark class for loading via `@LoadMe` (see [API-Docs](https://fuchss-dominik.github.io/rekit-game/))

## Step 3:
That's it. Just compile your library and add it to the `mods` folder (Windows: `%APPDATA%/rekit`, Unix/Mac: `~/.config/rekit`). Write a nice level and add it to the `levels` folder (the filename has to start with "level") and enjoy the game.
