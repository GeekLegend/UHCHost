package fr.geeklegend.world;

import fr.geeklegend.scheduler.StartScheduler;
import fr.geeklegend.Main;
import fr.geeklegend.game.GameManager;
import fr.geeklegend.util.Title;
import net.minecraft.server.v1_8_R3.ChunkProviderServer;
import org.bukkit.*;
import org.bukkit.World.Environment;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MapGenerator {

	private World w;
	private Environment env;
	private Integer x;
	private Integer dx;
	private Integer z;
	private Integer dz;
	private Integer threadnb = 1;

	private Boolean debug = false;
	private Boolean finish = false;
	private String state;
	private Boolean stateclalcul = false;;

	private DecimalFormat df = new DecimalFormat();
	private ChunkProviderServer cps;
	private Integer current = 0;
	private Integer nb;

	private BukkitTask task;
	private ArrayList<BukkitTask> asynctasklist;
	private Integer reload = null;
	private Long timestart;

	private Boolean shutdown = false;

	public MapGenerator(World w, Integer x, Integer dx, Integer z, Integer dz) {
		this.w = w;
		this.env = w.getEnvironment();
		this.x = x;
		this.dx = dx;
		this.z = z;
		this.dz = dz;
	}

	public MapGenerator(World w, Integer size) {
		this.w = w;
		this.env = w.getEnvironment();
		this.x = -size;
		this.dx = size;
		this.z = -size;
		this.dz = size;
	}

	public void setDebug(Boolean debug) {
		this.debug = debug;
	}

	public void setThreadnb(Integer threadnb) {
		this.threadnb = threadnb;
	}

	public void Break() {
		shutdown = true;
		if (task != null) {
			task.cancel();
		}
		if (reload != null) {
			Bukkit.getScheduler().cancelTask(reload);
		}
		if (asynctasklist != null) {
			for (BukkitTask bt : asynctasklist) {
				bt.cancel();
			}
		}
		setFinish(true);
	}

	public void start() {
		timestart = System.currentTimeMillis();
		generate();
	}

	private void generate() {

		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);

		ArrayList<Chunk> chl = new ArrayList<>();
		for (Chunk c : w.getLoadedChunks()) {
			chl.add(c);
		}

		task = Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(), () ->
		{

			setState("Starting...");

			x = Correctloc(x);
			dx = Correctloc(dx);
			z = Correctloc(z);
			dz = Correctloc(dz);

			cps = ((CraftWorld) w).getHandle().chunkProviderServer;
			nb = ((((dx - x) / 16) + 1) * (((dz - z) / 16) + 1));

			if (threadnb == 1) {
				stateclalcul = true;
				for (Integer cx = x / 16; cx <= dx / 16; cx++) {
					for (Integer cz = z / 16; cz <= dz / 16; cz++) {
						current++;
						Boolean chunkgen = false;
						for (Chunk c : chl) {
							if (c.getX() == cx && c.getZ() == cz) {
								chunkgen = true;
								break;
							}
						}
						if (!chunkgen) {
							cps.chunkProvider.getOrCreateChunk(cx, cz);
						}
						if (shutdown) {
							task.cancel();
						}
					}
				}
			} else {
				ArrayList<ArrayList<chunkscoord>> globalchunklist = new ArrayList<>();
				ArrayList<chunkscoord> chunklist = new ArrayList<>();
				for (Integer cx = x / 16; cx <= dx / 16; cx++) {
					for (Integer cz = z / 16; cz <= dz / 16; cz++) {
						if (chunklist.size() >= nb / threadnb && globalchunklist.size() - 1 != threadnb) {
							globalchunklist.add(new ArrayList<>(chunklist));
							chunklist.clear();
						}
						Boolean chunkgen = false;
						for (Chunk c : chl) {
							if (c.getX() == cx && c.getZ() == cz) {
								chunkgen = true;
								current++;
								break;
							}
						}
						if (!chunkgen) {
							chunklist.add(new chunkscoord(cx, cz));
						}
					}
				}
				globalchunklist.add(new ArrayList<>(chunklist));
				chunklist.clear();
				stateclalcul = true;
				ArrayList<Multithreadgenerator> mltthreadlist = new ArrayList<>();
				for (Integer currentthraed = 1; currentthraed <= threadnb; currentthraed++) {
					Multithreadgenerator mltthread = new Multithreadgenerator(
							globalchunklist.get(currentthraed - 1));
					mltthreadlist.add(mltthread);
				}
				Boolean globalfinish = false;
				while (!globalfinish) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					globalfinish = true;
					for (Multithreadgenerator m : mltthreadlist) {
						if (!m.isFinish()) {
							globalfinish = false;
							break;
						}
					}
				}
			}
			stateclalcul = false;
			Double t = (((double) System.currentTimeMillis()) - ((double) timestart)) / 1000;
			Double nbs = ((double) nb) / t;
			Bukkit.getConsoleSender().sendMessage(
					ChatColor.RED + "generation termine en: " + df.format(t) + "s  (soit: " + df.format(nbs) + ")");
			setState("Rechargement...");
			reloadmap();

			GameManager gameManager = Main.getPlugin().getGameManager();

			for (Player players : gameManager.getPlayers())
			{
				Title.sendTitle(players, 20, 20, 20, "§aALEX A UNE GROSSE BITE");
			}

			if (!StartScheduler.isRunning())
			{
				StartScheduler startScheduler = new StartScheduler();
				startScheduler.setRunning(true);
				startScheduler.runTaskTimer(Main.getPlugin(), 20, 20);
			}
		});
	}

	private void reloadmap() {

		reload = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () ->
		{
			Bukkit.unloadWorld(w, true);
			WorldCreator wc = new WorldCreator(w.getName());
			wc.environment(env);
			wc.createWorld();
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "rechargement termine");
			setState("Génération Terminée");
			setFinish(true);
			Bukkit.getScheduler().cancelTask(reload);
		}, 0, 1);
	}

	private Integer Correctloc(Integer i) {
		while (i % 16 != 0) {
			if (i < 0) {
				i--;
			} else if (i > 0) {
				i++;
			} else {
				break;
			}
		}
		return i;
	}

	private void setState(String state) {
		this.state = state;
	}

	private void setFinish(Boolean finish) {
		this.finish = finish;
	}

	public String getState() {
		if (stateclalcul) {
			Double pr = (((double) current) / ((double) nb)) * 100;
			String msgpr = df.format(pr);
			if (debug) {
				Bukkit.getConsoleSender().sendMessage("Generating chunk: " + current + "/" + nb + " Chunks   |   " + msgpr + "%");
			}
		}
		return state;
	}

	public Boolean isFinish() {
		return finish;
	}

	private class Multithreadgenerator {

		private Boolean taskfinish = false;

		public Multithreadgenerator(ArrayList<chunkscoord> list) {

			BukkitTask asynctask = Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(), () ->
			{
				for (chunkscoord c : list) {
					current++;
					cps.chunkProvider.getOrCreateChunk(c.getX(), c.getZ());
					if (shutdown) {
						break;
					}
				}
				taskfinish = true;
			});
			if (asynctasklist == null) {
				asynctasklist = new ArrayList<>();
			}
			asynctasklist.add(asynctask);
		}

		public Boolean isFinish() {
			return taskfinish;
		}
	}

	private class chunkscoord {

		private Integer x;
		private Integer z;

		public chunkscoord(Integer x, Integer z) {
			this.x = x;
			this.z = z;
		}

		public Integer getX() {
			return x;
		}

		public Integer getZ() {
			return z;
		}
	}
}