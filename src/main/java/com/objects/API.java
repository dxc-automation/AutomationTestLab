package com.objects;

public interface API {



  //  * * * *    S P O R T S B O O K

  String EXTERNAL_LOGIN = "/sportsbook/v1/api/externalLogin";
  String GET_OPEN_BETS  = "/sportsbook/v1/api/getOpenBets";
  String PLACE_BETS     = "/sportsbook/v1/api/placeBets";
  String CALCULATE_CASHOUT = "/sportsbook/v1/api/calculateCashout";
  String CASHOUT_BET       = "/sportsbook/v1/api/cashoutBet";
  String GET_SPORT_TREE    = "/sportsbook/v1/api/getSportTree";




  //  * * * *    R A M

  String RAM_LOGIN      = "/ram/login";
  String RAM_SESSION_ID = "/ram/login/sessionid";

  String WALLET_BET_STATUS = "/PsAmelcoApi/getBetStatus";




  //  * * * *    B A C K O F F I C E

  String BACKOFFICE_LOGIN  = "/sb-backoffice/v1/api/internalLogin";
  String BACKOFFICE_SEARCH = "/sb-backoffice/v1/api/searchBets";
  String BACKOFFICE_AMEND  = "/sb-backoffice/v1/api/amendBets";

  String BACKOFFICE_USERNAME = "test1";
  String BACKOFFICE_PASSWORD = "test1";
}
