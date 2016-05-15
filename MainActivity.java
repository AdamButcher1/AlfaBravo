import java.util.Hashtable;
import java.util.Scanner;

public class MainActivity
{

	public static boolean sentToLounge = false;
	public static boolean flyAgain = false;

	public static void main(String[] args)
	{

		Hashtable<String, Plane> plane = new Hashtable<String, Plane>();

		Scanner keyboard = new Scanner(System.in);
		String command;
		String name = "";
		int temp;
		boolean AlfaInitialized = false;
		boolean BravoInitialized = false;
		boolean LoungeInitialized = false;

		System.out.println("Welcome to Tree Top Airways - TTA!");
		System.out.println("Please set up the size of the ALFA and BRAVO planes");
		System.out.println("along with the lounge before using the ticket system.");
		System.out.println("Enter the following commands as accordingly.\n");

		System.out.println("ALFA <size>");
		System.out.println("BRAVO <size>");
		System.out.println("LOUNGE <size>");
		System.out.println("FLY <airplane>");
		System.out.println("ARRIVE <airplane> <party> <size>");
		System.out.println("SHUTDOWN\n");

		while (true)
		{
			System.out.print("Command: ");
			command = keyboard.nextLine();
			String delims = "[ ]+";
			String[] tokens = command.split(delims);
			tokens[0] = tokens[0].toUpperCase();

			switch (tokens[0])
			{
			case "ALFA":
				if (AlfaInitialized)
					System.out.println("ALFA is initialized already.");
				else
				{
					try
					{
						temp = Integer.parseInt(tokens[1]);

						if (temp <= 0)
							System.out
							        .println("Number can't be negative or none.");
						else
						{
							AlfaInitialized = true;
							plane.put("ALFA", new Plane(temp, "ALFA"));
						}
						System.out.println("Plane ALFA set to " + temp
						        + " seats.");
					} catch (Exception e)
					{
						System.out.println("Not a number.");
					}
				}

				break;
			case "BRAVO":
				if (BravoInitialized)
					System.out.println("BRAVO is initialized already.");
				else
				{
					try
					{
						temp = Integer.parseInt(tokens[1]);

						if (temp <= 0)
							System.out
							        .println("Number can't be negative or none.");
						else
						{
							BravoInitialized = true;
							plane.put("BRAVO", new Plane(temp, "BRAVO"));
						}
						System.out.println("Plane BRAVO set to " + temp
						        + " seats.");
					} catch (Exception e)
					{
						System.out.println("Not a number.");
					}
				}

				break;
			case "LOUNGE":
				if (LoungeInitialized)
					System.out.println("LOUNGE is initialized already.");
				else
				{
					try
					{
						temp = Integer.parseInt(tokens[1]);

						if (temp <= 0)
							System.out
							        .println("Number can't be negative or none.");
						else
						{
							LoungeInitialized = true;
							plane.put("LOUNGE", new Plane(temp, "LOUNGE"));
						}
						System.out.println("LOUNGE set to " + temp + " seats.");
					} catch (Exception e)
					{
						System.out.println("Not a number.");
					}
				}
				break;
			case "FLY":
				if (!AlfaInitialized || !BravoInitialized || !LoungeInitialized)
				{
					System.out
					        .println("Please initialize the planes and lounges first.");
				} else
				{
					tokens[1] = tokens[1].toUpperCase();

					if (plane.containsKey(tokens[1])
					        && !tokens[1].equals("LOUNGE"))
					{
						plane.get(tokens[1]).flyPlane();

						while (flyAgain)
						{
							for (int i = 0; i < plane.get("LOUNGE").parties
							        .size(); i++)
							{
								if (plane.get("LOUNGE").parties.get(i).size > plane
								        .get(tokens[1]).freeSeats)
								{
									continue;
								} else
								{

									plane.get(tokens[1])
									        .addParty(
									                tokens[1],
									                plane.get("LOUNGE").parties
									                        .get(i).name,
									                plane.get("LOUNGE").parties
									                        .get(i).size);
									plane.get("LOUNGE").freeSeats += plane
									        .get("LOUNGE").parties.get(i).size;
									plane.get("LOUNGE").parties.remove(i);
									i--;
								}

							}

							flyAgain = false;
						}

					} else
						System.out.println("Invalid plane");
				}
				break;
			case "ARRIVE":
				if (!AlfaInitialized || !BravoInitialized || !LoungeInitialized)
				{
					System.out
					        .println("Please initialize the planes and lounges first.");
				} else
				{

					tokens[1] = tokens[1].toUpperCase();

					if (plane.containsKey(tokens[1])
					        && !tokens[1].equals("LOUNGE"))
					{
						try
						{
							for (int i = 2; i < tokens.length - 1; i++)
							{
								if (i == tokens.length - 2)
									name += tokens[i];
								else
									name += tokens[i] + " ";
							}

							temp = Integer.parseInt(tokens[tokens.length - 1]);
							if (temp <= 0)
								System.out
								        .println("Number can't be negative or none.");
							else
							{
								plane.get(tokens[1]).addParty(tokens[1], name,
								        temp);
								if (sentToLounge)
								{
									plane.get("LOUNGE").addToLounge(tokens[1],
									        name, temp);
									sentToLounge = false;
								}
								name = "";
								while (flyAgain)
								{
									for (int i = 0; i < plane.get("LOUNGE").parties
									        .size(); i++)
									{
										if (plane.get("LOUNGE").parties.get(i).size > plane
										        .get(tokens[1]).freeSeats)
										{
											continue;
										} else
										{

											plane.get(tokens[1]).addParty(
											        tokens[1],
											        plane.get("LOUNGE").parties
											                .get(i).name,
											        plane.get("LOUNGE").parties
											                .get(i).size);
											plane.get("LOUNGE").freeSeats += plane
											        .get("LOUNGE").parties
											        .get(i).size;
											plane.get("LOUNGE").parties
											        .remove(i);
											i--;
										}

									}

									flyAgain = false;
								}
							}
						} catch (Exception e)
						{
							System.out.println("Not a number.");
						}
					} else
						System.out.println("Invalid plane.");

				}
				break;
			case "SHUTDOWN":

				if (!AlfaInitialized || !BravoInitialized || !LoungeInitialized)
				{
					System.out.println("Shutting down for the night.");
				} else
				{
					System.out.println("Shutting down for the night.");

					if (!plane.get("ALFA").parties.isEmpty())
						plane.get("ALFA").flyPlane();
					if (!plane.get("BRAVO").parties.isEmpty())
						plane.get("BRAVO").flyPlane();

					while (!plane.get("LOUNGE").parties.isEmpty())
					{
						for (int i = 0; i < plane.get("LOUNGE").parties.size(); i++)
						{
							if (plane.get("LOUNGE").parties.get(i).size > plane
							        .get(plane.get("LOUNGE").parties.get(i).which).freeSeats)
							{
								continue;
							} else
							{
								plane.get(
								        plane.get("LOUNGE").parties.get(i).which)
								        .addParty(
								                plane.get("LOUNGE").parties
								                        .get(i).which,
								                plane.get("LOUNGE").parties
								                        .get(i).name,
								                plane.get("LOUNGE").parties
								                        .get(i).size);
								plane.get("LOUNGE").freeSeats += plane
								        .get("LOUNGE").parties.get(i).size;
								plane.get("LOUNGE").parties.remove(i);
								i--;
							}
						}
						if (!plane.get("ALFA").parties.isEmpty())
							plane.get("ALFA").flyPlane();
						if (!plane.get("BRAVO").parties.isEmpty())
							plane.get("BRAVO").flyPlane();
					}
				}
				System.exit(0);
				break;
			default:
				System.out.println("Invalid command.");
				break;
			}

		}
	}
}
