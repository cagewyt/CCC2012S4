import java.util.*;

/**
 *
 * @author taylor
 */
public class Main {
	// andy made this
	public static int n = 0;
	public static void main(String[] args) {
		// TODO code application logic here
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> res = new ArrayList<Integer> ();
		while (true) {
			n = in.nextInt();
			if (n == 0)
				break;
			int[] init = new int[n];
			for (int i = 0; i < n; i++) {
				init[i] = in.nextInt() - 1;
			}
			Config conf = new Config();
			conf.init(init);
			res.add(bfs(conf));
			
		}
		for(Integer re : res)
		{
			if (re == -1) {
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println(re);
				//adsfasdfadf
				
			}
		}
	}

	public static boolean isSolution(Config conf) {
		for (int i = 0; i < n; i++) {
			ArrayList<Integer> arr = conf.config.get(i);
			if (!(arr.size() == 1 && arr.get(0).equals(i))) {
				return false;
			}
		}
		return true;
	}

	public static int bfs(Config conf) {
		HashSet<Config> level = new HashSet<Config>();
		level.add(conf);
		HashSet<Config> visited = new HashSet<Config>();
		visited.add(conf);
		int step = 0;
		while (!level.isEmpty()) {
			// level contain solution ?
			for (Config con : level) {
				if (isSolution(con)) {
					return step;
				}
			}

			// add to visited
			step++;
			visited.addAll(level);

			// find next level
			HashSet<Config> nextLevel = new HashSet<Config>();

			//
			for (Config con : level) {
				for (int i = 0; i < n; i++) {
					if (i > 0 && con.config.get(i).size() > 0) {
						Config left = con.clone();

						if (left.config.get(i - 1).size() == 0
								|| left.config.get(i - 1).get(0) > left.config
										.get(i).get(0)) {
							left.config.get(i - 1).add(0,
									left.config.get(i).get(0));
							left.config.get(i).remove(0);

							if (!nextLevel.contains(left)
									&& !visited.contains(left)) {
								nextLevel.add(left);
							}
						}
					}

					if (i < n - 1 && con.config.get(i).size() > 0) {
						Config right = con.clone();

						if (right.config.get(i + 1).size() == 0
								|| right.config.get(i + 1).get(0) > right.config
										.get(i).get(0)) {
							right.config.get(i + 1).add(0,
									right.config.get(i).get(0));
							right.config.get(i).remove(0);

							if (!nextLevel.contains(right)
									&& !visited.contains(right)) {
								nextLevel.add(right);
							}
						}
					}

				}
			}
			// printLevel(nextLevel);
			level = nextLevel;
		}

		return -1;
	}

	public static void printLevel(HashSet<Config> level) {
		if (level.size() == 0)
			return;
		System.out.println("//////////////////////");
		for (Config con : level) {
			System.out.println(con.config);
		}
		System.out.println("//////////////////////");
	}
}

class Config {

	public ArrayList<ArrayList<Integer>> config = new ArrayList<ArrayList<Integer>>();

	public Config() {

	}

	public void init(int[] init) {
		for (int i = 0; i < init.length; i++) {
			config.add(new ArrayList<Integer>());
			config.get(i).add(init[i]);
		}
	}

	public Config clone() {
		Config newConfig = new Config();
		for (ArrayList<Integer> arr : this.config) {
			newConfig.config.add((ArrayList<Integer>) arr.clone());
		}
		return newConfig;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((config == null) ? 0 : config.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Config other = (Config) obj;
		if (config == null) {
			if (other.config != null)
				return false;
		} else if (!config.equals(other.config))
			return false;
		return true;
	}

}
