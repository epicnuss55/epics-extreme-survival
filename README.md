- minecraft Forge 1.16.5 - 35.1.4

# INTRO
This is my own take on a Tough As Nails type of Minecraft mod. This mod adds thirst and hyperthermia and hypothermia depending on what biome you're in. If you wish to play on this mod, then please keep in mind that this version is kind of more tedious than fun.


# CHANGELOGS
## DD/MM/YYYY

### 23/05/2021
- added a thirst effect from drinking from water source blocks
- added thirst effect saver

### 20/05/2021
- added ability to drink from water source blocks

### 18/05/2021
- added the ability for fire resistance potions to negate heat damage

### 13/05/2021
- added a temperature saving system

### 11/05/2021
- finalized temperature overlay
- finalized temperature overlay's opacity slope

### 07/05/2021
- changed hot and cold gradient

### 05/05/2021
- changed delay for body temperature change

### 1/05/2021
- created the render logic for temperature overlays
- added blur for freezing and burning overlays

### 29/04/2021
- added burning and freezing overlays

### 24/04/2021
- heat damage
- cold damage
- Temperature rise and fall logic

### 22/04/2021
- Added basic biome checker logic
- Added a cheecky missing biome (GIANT_TREE_TAIGA_HILLS)

### 20/04/2021
- Finished biome temperature registries
- added temperature registries for biome 160 to 173 (numerical id)

### 13/04/2021
- removed isIncreasing scale and refactored all current temperatures for current biomes
- added temperature registries for biome 21 to 158 (numerical id)

### 09/04/2021
- removed god apple, enchanted god apple and chorus fruit juice class & swapped it with advanced juice class
- added advanced juice abstract class (for custom juices with custom effects)
- changed the juice items in ItemInit accordingly
- added temperature registries for biome 16 to 20 (numerical id)

### 08/04/2021
- added back accidentally deleted bamboo filterer item
- removed all unnecessary juice classes
- converted all drinkables in ItemInit to a JuiceItem
- added JuiceItem class
- added temperature registries for biomes 6 to 15 (numerical id)

### 07/04/2021
- added ocean, plains, desert, mountains, forest and taiga temperature registries
- added temperature increase/decrease
- added golden carrot juice lang

### 05/04/2021
- made a custom temperature register (for each biome)

### 31/03/2021
- added crafting for all the juices
- Added lang for all the juices
- Added ability for Chorus Fruit Juice to teleport you randomly

### 28/03/2021
- added enchanted golden apple juice glint
- made juices texture
- added juices textures
- added registered the juices
- added apple juice
- added beetroot juice
- added carrot juice
- added chorus fruit juice
- added enchanted golden apple juice
- added golden apple juice
- added golden carrot juice
- added melon juice
- added sweet berry juice

### 27/03/2021
- purified water lang
- purified water texture
- added the ability for lily pad filterer to give purified water
- added purified water bottle
- changed up the drink water bottle event
- added honey to the drink water bottle event
- added bamboo filter recipe
- added bamboo filter textures
- added bamboo filter lang

### 26/03/2021
- added new bamboo filterer (basically a portable lilypad filterer with limitations)
- added bamboo filterer full state
- added bamboo filterer empty state

### 22/03/2021
- changed the lilypad filter model abit
- added dirty water animations
- added clean water animations
- added lilypad filterer crafting recipe
- added lilypad filterer to the lang
- added an addWater method
- finished the lilypad filterer logic

### 18/03/2021
- added block models
- removed tile entity (probably would add back later)
- changed textures
- added functionality to lilypad filter (HEAVILY BUGGED)

### 14/03/2021
- added proccessing and proccessed block states in lilypad filter system
- added tile entities for proccessed and proccessing block states in lilypad filter system
- added loot tables for proccessed and proccessing blcok states in lilypad filter system

### 12/03/2021
- tweaked the lilypad filter model a bit

### 11/03/2021
- added block for the lilypad filtration system inspired by [this](https://www.wateronline.com/doc/sun-activated-lilypads-remove-contaminants-without-chemicals-0001#:~:text=Each%20LilyPad%20can%20treat%201,founder%20and%20CEO%20of%20Puralytics.)
- added model for the lilypad filtration system (subject to change)
- added ModBlock register

### 09/03/2021
- bobbing effect looks nicer
- added ability for half water icon to show movement every bob
- animated frames last for a bit longer

### 07/03/2021
- added bobbing effect to water icons (depending on how low the thirst is correlates to the frequency of the animation)

### 06/03/2021
- finally fixed the saving system (hopefully won't break again)

### 03/03/2021
- fixed bug where thirst was depleting in creative mode
- fixed bug where thirst would deplete in peaceful mode and kill you on easy and normal
- fixed up thirst saving system

### 01/03/2021
- all thirst related bugs (probably) fixed
- balanced dehydration
- fixed player not being damaged when suffering from terminal dehydration bug

### 27/02/2021
- bug fixes in dehydration logic
- attempt to balance the dehydration amount

### 25/02/2021
- gave thirst its own exhaustion (so it no longer piggy-backs off of whenever the hunger bar changes)

### 24/02/2021
- fixed player not healing bug
- added the ability to regain water by drinking out of a bottle

### 22/02/2021
- fixed the bug were as soon as you respawn, you instantly loose half a thirst

### 21/02/2021
- Thirst bar & thirst logic
- placeholder Item
- Item group
- death resets thirst
- health wont restore until both hunger & food are both maxed out

### 20/02/2021
- initial commit & created mod workspace