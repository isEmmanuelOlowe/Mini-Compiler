
	PROC FLOWER ( LEVEL )
	    IF LEVEL == 0 THEN
		FORWARD 0
	    ELSE
		FORWARD LEVEL
		RIGHT 185
		LFLOWER  ( LEVEL - 1 )
	    ENDIF 

	PROC LFLOWER ( LEVEL )
	    IF LEVEL == 0 THEN
		FORWARD 0
	    ELSE
		FORWARD LEVEL
		RIGHT 25
		RFLOWER ( LEVEL - 1 )
	    ENDIF 
	
	PROC RFLOWER ( LEVEL )
	    IF LEVEL == 0 THEN
		FORWARD 0
	    ELSE
		FORWARD LEVEL
		LEFT 60
		FLOWER  ( LEVEL - 1 )
	    ENDIF 

	PROC MAIN (VOID)
	   FLOWER  ( 140 )