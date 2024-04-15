package com.piece;

import main.GamePanel;
import main.Type;

public class Pawn  extends Piece{

	public Pawn(int color, int col, int row) {
		super(color, col, row);
		
		type = Type.PAWN;
		
		if (color == GamePanel.WHITE) {
			image = getImage("/Piece/wp"); 
		}
		else{
			image = getImage("/Piece/bp");
		}
	}
	public boolean canMove(int targetCol, int targetRow) {
		
		if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false) {
			
			//Difine the move value bases on its color
			int moveValue;
			if(color == GamePanel.WHITE) {
				moveValue = -1;
				
			}
			else {
				moveValue = 1;
			}
			
			//check the hitting piece
			hittingP =getHittingP(targetCol, targetRow);
			
			// 1 square movment
			if(targetCol ==preCol && targetRow == preRow +moveValue && hittingP ==null) {
				return true;
			}
			// 2 Squer Movement
			if(targetCol == preCol && targetRow == preRow + moveValue*2 && hittingP == null && moved == false &&
					pieceIsOnStraightLine(targetCol, targetRow) == false) {
				return true;
			}
			// Diagonal movement & Capture (if a piece is on a Squer diagonally in front of it )
			if (Math.abs(targetCol -preCol)==1 && targetRow == preRow + moveValue && hittingP != null && hittingP.color != color) {
				return true;
			}
			
			// En Passant
			if(Math.abs(targetCol - preCol)== 1 && targetRow == preRow + moveValue) {
				for (Piece piece : GamePanel.simpieces) {
				if  (piece.col == targetCol && piece.row == preRow && piece.twoStepped == true) {
					hittingP = piece;
					return true;
				}
			}
			}
			
				}
		return false;
	}

}
