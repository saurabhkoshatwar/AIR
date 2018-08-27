import java.util.*;

class Planner
{
int k;

int[][] on;
int[] ontable;
int[] clear;
int[] holding;
int armempty;


String start;
String goal;


Scanner sc = new Scanner(System.in);
	
	Stack<String> goalstack = new Stack<>();

	Planner(int n)
	{
		k=n;
		on = new int[n][n];
		ontable = new int[n];
		clear = new int[n];
		holding = new int[n];
		armempty = 1;	
	
	}
	public void input()
	{

		/*System.out.println("Enter Start State");
		start = sc.next();
		current = start;
		System.out.println("Enter Start State");
		goal = sc.next();*/
		start = "ontable(a)^ontable(c)^on(b,a)^clear(a)^clear(C)^armempty";
		goal = "clear(A)^ontable(C)^on(A,B)^on(B,C)^armempty";		
	};
		
	public void set_initial()
	{
		String sg[] = start.split("['^']");
		for(int i=0;i<sg.length;i++)
		{	
			if(sg[i].contains("ontable"))
			{
				ontable[sg[i].charAt(8)] = 1;
			}
			else if(sg[i].contains("on"))
			{
				on[sg[i].charAt(3)][sg[i].charAt(5)] = 1;
			}
			else if(sg[i].contains("clear"))
			{
				clear[sg[i].charAt(6)] = 1;
			}
			else if(sg[i].contains("holding"))
			{
				holding[sg[i].charAt(8)] = 1;
			}
			else if(sg[i].contains("armempty"))
			{
				armempty = 1;
			}
			
		}
	}	
	
	public boolean check(String state)
	{	
		if(state.contains("^"))
		{
				
			String simple_state[] = start.split("['^']");
			
			for(int i=0;i<simple_state.length;i++)
			{
				if(!check_simple(simple_state[i]))
				{
					return false;
				} 	
			}
			
			return true;
			
		}
		else
		{
			return check_simple(state);
		}
	}	
	
	public boolean check_simple(String state)
	{
			if(state.contains("ontable") && ontable[state.charAt(8)] == 1)
			{
				return true;
			}
			else if(state.contains("on") && on[state.charAt(3)][state.charAt(5)] == 1)
			{
				return true;
			}
			else if(state.contains("clear") && clear[state.charAt(6)] == 1)
			{
				return true;
			}
			else if(state.contains("holding") && holding[state.charAt(8)] == 1)
			{
				return true;
			}
			else if(state.contains("armempty") && armempty == 1)
			{
				return true;
			}
			else
			{
				return false;
			}			
	}
	
	public void plan()
	{
		goalstack.push(goal);
		String sg[] = start.split("['^']");
		String current;
		while(!goalstack.isEmpty())
		{
			current = goalstack.pop();
			
			if(check.contains("^"))
			{
				if(check(current))
				{
					goalstack.pop();
				}
				else{
					goalstack.push(current);
					String sg[] = current.split("['^']");
					for(int i=0;i<sg.length;i++)
					{
						goalstack.push(sg[i])
					}
				}
			}
		}
		
		
	}

	public static void main(String args[])
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Enter no of block");
		int k = s.nextInt();
		Planner p = new Planner(k);
		p.input();
		p.set_initial();
	
	}
}
