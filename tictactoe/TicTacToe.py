import math

# -----------------------------
# 1. Definér globale variabler
# -----------------------------

# Vi bruger en liste med 9 elementer for at repræsentere brættet.
# Indeks i listen svarer til felter på TicTacToe-brættet:
#  0 | 1 | 2
# -----------
#  3 | 4 | 5
# -----------
#  6 | 7 | 8
#
# Tomme felter markeres med ' ', spilleren (mennesket) er 'X', computeren er 'O'.
board = [" ", " ", " ",
         " ", " ", " ",
         " ", " ", " "]

# Spilleren ('X') starter som udgangspunkt.
current_player = "X"


# --------------------------------
# 2. Funktion til at printe brættet
# --------------------------------
def print_board():
    """
    Printer spillets nuværende bræt i et overskueligt format.
    """
    print("\n")
    print(" {} | {} | {} ".format(board[0], board[1], board[2]))
    print("-----------")
    print(" {} | {} | {} ".format(board[3], board[4], board[5]))
    print("-----------")
    print(" {} | {} | {} ".format(board[6], board[7], board[8]))
    print("\n")


# ------------------------------------------------
# 3. Funktion der tjekker, om der er en vinder eller uafgjort
# ------------------------------------------------
def check_winner(b):
    """
    Returnerer 'X' hvis spilleren 'X' har vundet,
    Returnerer 'O' hvis spilleren 'O' har vundet,
    Returnerer 'D' (for Draw) hvis der er uafgjort,
    Returnerer None hvis ingen har vundet endnu.
    """

    # Definér alle mulige vindende kombinationer af indekser
    winning_combinations = [
        (0, 1, 2),  # vandret øverste række
        (3, 4, 5),  # vandret midterste række
        (6, 7, 8),  # vandret nederste række
        (0, 3, 6),  # lodret venstre
        (1, 4, 7),  # lodret midter
        (2, 5, 8),  # lodret højre
        (0, 4, 8),  # diagonal (venstre top -> højre bund)
        (2, 4, 6)   # diagonal (højre top -> venstre bund)
    ]

    # Tjek alle vindende kombinationer
    for combo in winning_combinations:
        if b[combo[0]] == b[combo[1]] == b[combo[2]] != " ":
            # Hvis alle felter i en kombination er ens og ikke er " ", har vi en vinder
            return b[combo[0]]  # enten 'X' eller 'O'

    # Hvis alle felter er udfyldt, og der ikke er fundet nogen vinder, er det uafgjort
    if all(field != " " for field in b):
        return "D"  # Draw

    # Ingen vinder, ikke uafgjort endnu
    return None


# --------------------------------------------
# 4. Minimax-funktion: Finder bedste træk for AI
# --------------------------------------------
def minimax(b, is_maximizing):
    """
    b = nuværende tilstand af brættet (liste)
    is_maximizing = True, hvis det er AI'ens tur (vi maksimerer AI'ens score)
                    False, hvis det er menneskets tur (vi minimerer AI'ens score)
    Returnerer en "score" for, hvor godt brættet er for AI'en (O).
    """

    # Tjek først om der er en terminal-tilstand (vinder eller uafgjort)
    result = check_winner(b)
    if result == "X":
        return -1  # Hvis 'X' (mennesket) har vundet, er det dårligt for O -> score -1
    elif result == "O":
        return 1   # Hvis 'O' (computeren) har vundet -> score 1
    elif result == "D":
        return 0   # Uafgjort -> score 0

    # Hvis spillet ikke er slut, fortsætter vi
    if is_maximizing:
        best_score = -math.inf  # Start med laveste mulige score
        for i in range(9):
            if b[i] == " ":  # hvis feltet er ledigt
                b[i] = "O"   # prøv at sætte O (AI'ens træk)
                score = minimax(b, False)
                b[i] = " "   # fortryd trækket (backtrack)
                best_score = max(best_score, score)
        return best_score
    else:
        best_score = math.inf  # Start med højeste mulige score
        for i in range(9):
            if b[i] == " ":  # hvis feltet er ledigt
                b[i] = "X"   # prøv at sætte X (menneskets træk)
                score = minimax(b, True)
                b[i] = " "   # fortryd trækket
                best_score = min(best_score, score)
        return best_score


def best_move_for_ai():
    """
    Finder det bedste træk for computeren ('O') ved at bruge minimax.
    Returnerer indeks (0-8) for det bedste træk.
    """
    best_score = -math.inf
    move = None

    for i in range(9):
        if board[i] == " ":
            board[i] = "O"  # prøv at sætte O i feltet
            score = minimax(board, False)
            board[i] = " "  # fortryd trækket
            if score > best_score:
                best_score = score
                move = i

    return move


# -------------------------------------------------
# 5. Spil-loop: Håndterer tur-skift og brugerinput
# -------------------------------------------------
def main():
    global current_player

    # Så længe der ikke er en vinder eller uafgjort ...
    while True:
        print_board()
        result = check_winner(board)
        if result == "X":
            print("Spiller 'X' har vundet!")
            break
        elif result == "O":
            print("Computeren (O) har vundet!")
            break
        elif result == "D":
            print("Det blev uafgjort!")
            break

        if current_player == "X":
            # Spillerens tur
            try:
                # Spørg brugeren om et felt (0-8)
                move = int(input("Vælg et felt (0-8): "))
                if move < 0 or move > 8:
                    print("Ugyldigt felt. Indtast tal mellem 0 og 8.")
                    continue
                if board[move] != " ":
                    print("Feltet er allerede optaget. Vælg et andet.")
                    continue

                # Placer 'X' på brættet
                board[move] = "X"
                # Skift tur
                current_player = "O"

            except ValueError:
                print("Ugyldigt input. Indtast et tal mellem 0 og 8.")
        else:
            # Computerens tur
            move = best_move_for_ai()
            board[move] = "O"
            # Skift tur
            current_player = "X"

    # Print til sidst det endelige bræt, når spillet er afsluttet
    print_board()


if __name__ == "__main__":
    main()
