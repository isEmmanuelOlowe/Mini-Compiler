
	PROC LDRAGON ( LEVEL )
	    IF LEVEL == 0 THEN
		FORWARD 5 
	    ELSE
	    FORWARD 20
	    LEFT LEVEL
		LDRAGON ( LEVEL - 1 )
	    ENDIF 

	PROC MAIN (VOID )
	   LDRAGON ( 300 )
