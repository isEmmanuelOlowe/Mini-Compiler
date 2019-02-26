
	PROC LDRAGON ( LEVEL )
	    IF LEVEL == 0 THEN
		FORWARD 5
	    ELSE
		LDRAGON ( LEVEL -  ( 1  * 5 ) + 4 + 10 - ( 2 * 5 ) )
		LEFT 90
		RDRAGON ( LEVEL - ( 1 + 1 - 1 + 1  - 5 * 2 + 9) )
	    ENDIF


	PROC RDRAGON ( LEVEL )
	    IF LEVEL == (5 * 7 - 35) THEN
		FORWARD (50 - 45)
	    ELSE
		LDRAGON ( LEVEL - ( ( ( ( ( 1 ) ) ) ) ) )
		RIGHT 90
		RDRAGON ( LEVEL - (50 - 49 * 1) )
	    ENDIF

	PROC MAIN (VOID)
	   LDRAGON ( 12 - (50 - 49) )
