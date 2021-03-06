package com.TTTServer.Presenters;

import com.TTTServer.TTTLogic.TTTBoardState;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TTTBoardPresenter {

    public static byte[] presentBoard(TTTBoardState boardState) {
        byte[] stylesheet = getStylesheet();
        byte[] header = getHeader();
        byte[] boardRepresentation = getBoardRepresentation(boardState);
        byte[] script = getScript();

        int stylesheetLength = stylesheet.length;
        int headerLength = header.length;
        int boardRepresentationLength = boardRepresentation.length;
        int scriptLength = script.length;

        int totalPageLength = stylesheetLength + headerLength + boardRepresentationLength + scriptLength;
        byte[] wholePage = new byte[totalPageLength];

        for (int i = 0; i < totalPageLength; i++) {
            if (i < stylesheetLength) {
                wholePage[i] = stylesheet[i];
            } else if (i < stylesheetLength + headerLength) {
                wholePage[i] = header[i - stylesheetLength];
            } else if (i < stylesheetLength + headerLength + boardRepresentationLength) {
                wholePage[i] = boardRepresentation[i - stylesheetLength - headerLength];
            } else {
                wholePage[i] = script[i - stylesheetLength - headerLength - boardRepresentationLength];
            }
        }

        return wholePage;
    }

    private static byte[] getStylesheet() {
        try {
            Path tttBoardStylesheetPath = Paths.get(System.getProperty("user.dir") +
                    "/src/com/TTTServer/Presenters/Assets/TTTBoardStylesheet.txt");
            return Files.readAllBytes(tttBoardStylesheetPath);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] getHeader() {
        try {
            Path tttBoardHeaderPath = Paths.get(System.getProperty("user.dir") +
                    "/src/com/TTTServer/Presenters/Assets/TTTBoardHeader.txt");
            return Files.readAllBytes(tttBoardHeaderPath);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] getBoardRepresentation(TTTBoardState boardState) {
        String boardRepresentation = "<div class=\"container\">\n";
        if (boardState.isGameOver()) {
            boardRepresentation += "<div class=\"game-over\">\n" +
                    "<div class=\"contents\">\n" +
                    "<h1>Game Over!</h1>\n" +
                    "<h2>Play again?</h2>\n" +
                    "<a href=\"game\"><button type=\"submit\">Standard Game</button></a>\n" +
                    "<a href=\"config\"><button type=\"submit\">Custom Game</button></a>\n" +
                    "</div>\n" +
                    "</div>\n";
        }
        boardRepresentation += boardTable(boardState.getBoard(), boardState.getPlayer1Mv(), boardState.getPlayer2Mv(),
                boardState.getPlayer1Id(), boardState.getPlayer2Id());
        boardRepresentation += "</div>\n";
        return boardRepresentation.getBytes();
    }

    private static byte[] getScript() {
        try {
            Path tttBoardScriptPath = Paths.get(System.getProperty("user.dir") +
                    "/src/com/TTTServer/Presenters/Assets/TTTBoardScript.txt");
            return Files.readAllBytes(tttBoardScriptPath);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private static String boardTable(ArrayList<String> board, String player1Move, String player2Move, String player1Id,
                                     String player2Id) {
        int boardSize = board.size();
        String boardTable = "<table>\n<tbody>\n<tr>\n";
        for (int i = 0; i < boardSize; i++) {
            boardTable += cellContentsAsTableData(boardCellContents(board.get(i), player1Move, player2Move, player1Id,
                    player2Id, board, i + 1), i + 1);
            if (isRowSeparator(i, boardSize)) {
                boardTable += "</tr>\n<tr>\n";
            }
        }
        boardTable += "</tr>\n</tbody>\n</table>\n";
        return boardTable;
    }

    private static String cellContentsAsTableData(String cellContents, int index) {
        String tableData = "<td class=\"square";
        if (isInCenterColumn(index)) {
            tableData += " cc";
        }
        if (isInCenterRow(index)) {
            tableData += " cr";
        }
        if (!cellContents.contains("h1")) {
            tableData += " unchecked";
        }
        tableData += "\" id=\"square-" + index + "\">\n";
        tableData += cellContents + "</td>\n";
        return tableData;
    }

    private static boolean isRowSeparator(int index, int boardSize) {
        return index % 3 == 2 && index < boardSize - 1;
    }

    private static boolean isInCenterColumn(int index) {
        return index % 3 == 2;
    }

    private static boolean isInCenterRow(int index) {
        return index > 3 && index < 7;
    }

    private static String boardCellContents(String cell, String player1Move, String player2Move,
                                           String player1Id, String player2Id, ArrayList<String> board, int index) {
        String cellContents = "";
        if (!cell.equals(" ")) {
            cellContents += "<h1 class=\"";
            if (cell.equals(player1Move)) {
                cellContents += "player-1\">";
            } else {
                cellContents += "player-2\">";
            }
            cellContents += cell + "</h1>\n";
        } else {
            cellContents += "<form id=\"form-" + index + "\" method=\"post\">\n";
            cellContents += "<input type=\"hidden\" name=\"player-1-id\" value=\"" + player1Id + "\">\n";
            cellContents += "<input type=\"hidden\" name=\"player-2-id\" value=\"" + player2Id + "\">\n";
            cellContents += "<input type=\"hidden\" name=\"player-1-mv\" value=\"" + player1Move + "\">\n";
            cellContents += "<input type=\"hidden\" name=\"player-2-mv\" value=\"" + player2Move + "\">\n";
            cellContents += "<input type=\"hidden\" name=\"board\" value=\"" + boardToString(board) + "\">\n";
            cellContents += "<input type=\"hidden\" name=\"move\" value=\"" + index + "\">\n";
            cellContents += "</form>\n";
        }
        return cellContents;
    }

    private static String boardToString(ArrayList<String> board) {
        String boardString = "";
        int boardSize = board.size();
        for (int i = 0; i < boardSize; i++) {
            boardString += board.get(i);
            if (i < boardSize - 1) {
                boardString += ",";
            }
        }
        return boardString;
    }
}
