# speedback

Just a simple script to generate all speed feedback sessions pairs, given participants.

## Usage

The program expect a list of participants separated by `,`

    $ lein run "person 1,person 2,person 3,..."

## Examples

Ok, let's pretend I'll have a speed feedback session with all my pets.

### Given even number of participants

Input:

    $ lein run "Renata,Princesa Zelda,Docinho,Dona Nina,Lady,Gigi"

Output:

```
Round 1:
Renata - Lady
Princesa Zelda - Dona Nina
Docinho - Gigi

Round 2:
Lady - Dona Nina
Renata - Docinho
Princesa Zelda - Gigi

Round 3:
Dona Nina - Docinho
Lady - Princesa Zelda
Renata - Gigi

Round 4:
Docinho - Princesa Zelda
Dona Nina - Renata
Lady - Gigi

Round 5:
Princesa Zelda - Renata
Docinho - Lady
Dona Nina - Gigi

The total duration of this session is 59min, given:
5min introduction
10min exchanging feedback per pair
1min switching beetween pairs

```

But Zelda said she is busy saving hyrule with Link (she is actually sleeping in the closet, that lazy cat). Now we have and odd number of participants.

### Given odd number of participants

Every round we'll have a different participant just waiting.

Input:

     $ lein run "Renata,Docinho,Dona Nina,Lady,Gigi" 

Output:

```
Round 1:
Renata - Gigi
Docinho - Lady
Dona Nina - waiting

Round 2:
Gigi - Lady
Renata - Dona Nina
Docinho - waiting

Round 3:
Lady - Dona Nina
Gigi - Docinho
Renata - waiting

Round 4:
Dona Nina - Docinho
Lady - Renata
Gigi - waiting

Round 5:
Docinho - Renata
Dona Nina - Gigi
Lady - waiting

The total duration of this session is 59min, given:
5min introduction
10min exchanging feedback per pair
1min switching beetween pairs

```
