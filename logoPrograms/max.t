
	PROC MAX ( LEVEL )
	    IF LEVEL == 0 THEN
		FORWARD 0
	    ELSE
		FORWARD LEVEL
		RIGHT 85
		LMAX ( LEVEL - 1 )
	    ENDIF 

	PROC LMAX ( LEVEL )
	    IF LEVEL == 0 THEN
		FORWARD 0
	    ELSE
		FORWARD LEVEL
		LEFT 185
		MAX ( LEVEL - 1 )
	    ENDIF 

	PROC MAIN (VOID)
	   MAX ( 150 )