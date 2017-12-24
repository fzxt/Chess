package com.company.gui.promotion;

import com.company.Team;
import com.company.piece.Piece;

public interface PromotionGUIContract {

    interface Presenter {
        /**
         * Handles promotion after user has selected what piece they want to promote to.
         * @param selectedPiece         The selected piece to promote to.
         */
        void handlePromotion(Piece selectedPiece);

        Team getTeam();
    }

    interface View {
        void setPresenter(PromotionGUIContract.Presenter presenter);

        /**
         * Shows the view
         */
        void showView();

        /**
         * Hides the view
         */
        void hideView();
    }

}
