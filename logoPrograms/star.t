
	PROC STAR ( LEVEL )
	    IF LEVEL == 0 THEN
		FORWARD 0
	    ELSE
		FORWARD LEVEL
		RIGHT 95
		LSTAR ( LEVEL - 1 )
	    ENDIF 

	PROC LSTAR ( LEVEL )
	    IF LEVEL == 0 THEN
		FORWARD 0
	    ELSE
		FORWARD LEVEL
		LEFT 195
		STAR ( LEVEL - 1 )
	    ENDIF 

	PROC MAIN (VOID)
	   STAR ( 150 )