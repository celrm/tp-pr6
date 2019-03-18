package simulator.launcher;

/*
 Examples of command-line parameters:
 
 -h
 -i resources/examples/ex4.4body.txt -s 100
 -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100
 -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100 -gl ftcg
 -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100 -gl nlug
 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.json.JSONObject;

import simulator.control.Controller;
import simulator.factories.BasicBodyBuilder;
import simulator.factories.Builder;
import simulator.factories.BuilderBasedFactory;
import simulator.factories.Factory;
import simulator.factories.FallingToCenterGravityBuilder;
import simulator.factories.MassLosingBodyBuilder;
import simulator.factories.NewtonUniversalGravitationBuilder;
import simulator.factories.NoGravityBuilder;
import simulator.model.Body;
import simulator.model.GravityLaws;
import simulator.model.PhysicsSimulator;
import simulator.view.MainWindow;

public class Main {

	// default values for some parameters
	private final static Double _dtimeDefaultValue = 2500.0;
	private final static String _modeDefaultValue = "batch";
	private final static int _stepsDefaultValue = 150;

	// some attributes to stores values corresponding to command-line parameters
	private static Double _dtime = null;
	private static String _inFile = null;
	private static String _outFile = null;
	private static String _mode = null;
	private static int _steps = _stepsDefaultValue; // El resto tiene null pero como este es int no
	private static JSONObject _gravityLawsInfo = null;

	// factories
	private static Factory<Body> _bodyFactory;
	private static Factory<GravityLaws> _gravityLawsFactory;

	private static void init() {
		ArrayList<Builder<Body>> bodyBuilders = new ArrayList<>();
		bodyBuilders.add(new BasicBodyBuilder());
		bodyBuilders.add(new MassLosingBodyBuilder());
		_bodyFactory = new BuilderBasedFactory<Body>(bodyBuilders);
		
		ArrayList<Builder<GravityLaws>> lawBuilders = new ArrayList<>();
		lawBuilders.add(new NewtonUniversalGravitationBuilder());
		lawBuilders.add(new FallingToCenterGravityBuilder());
		lawBuilders.add(new NoGravityBuilder());
		_gravityLawsFactory = new BuilderBasedFactory<GravityLaws>(lawBuilders);
	}

	private static void parseArgs(String[] args) {

		// define the valid command line options
		Options cmdLineOptions = buildOptions();

		// parse the command line as provided in args
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseModeOption(line); // Celia (importante antes que infile)
			parseHelpOption(line, cmdLineOptions);
			parseInFileOption(line);
			parseOutFileOption(line); // Celia
			parseStepsOption(line); // Celia
			parseDeltaTimeOption(line);
			parseGravityLawsOption(line);

			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}

	private static Options buildOptions() {
		Options cmdLineOptions = new Options();

		// batch mode
		cmdLineOptions.addOption(Option.builder("m").longOpt("mode").hasArg()
				.desc("Execution Mode. Possible values: ’batch’ (Batch mode), "
						+ "’gui’ (Graphical User Interface mode). Default value: "
						+ _modeDefaultValue + ".")
				.build());

		// help
		cmdLineOptions.addOption(Option.builder("h").longOpt("help")
				.desc("Print this message.")
				.build());

		// input file
		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg()
				.desc("Bodies JSON input file.")
				.build());

		// output file // Celia
		cmdLineOptions.addOption(Option.builder("o").longOpt("output").hasArg()
				.desc("Output file, where output is written. Default value: "
						+ "the standard output.")
				.build());
		
		// steps // Celia
		cmdLineOptions.addOption(Option.builder("s").longOpt("steps").hasArg()
				.desc("An integer representing the number of simulation steps. Default value: "
						+ _stepsDefaultValue + ".")
				.build());

		// delta-time
		cmdLineOptions.addOption(Option.builder("dt").longOpt("delta-time").hasArg()
				.desc("A double representing actual time, in seconds, per simulation step. Default value: "
						+ _dtimeDefaultValue + ".")
				.build());

		// gravity laws -- there is a workaround to make it work even when
		// _gravityLawsFactory is null.
		//
		String gravityLawsValues = "N/A";
		String defaultGravityLawsValue = "N/A";
		if (_gravityLawsFactory != null) {
			gravityLawsValues = "";
			for (JSONObject fe : _gravityLawsFactory.getInfo()) {
				if (gravityLawsValues.length() > 0)
					gravityLawsValues = gravityLawsValues + ", ";
				gravityLawsValues = gravityLawsValues + "'" + fe.getString("type") + "' (" + fe.getString("desc") + ")";
			}
			defaultGravityLawsValue = _gravityLawsFactory.getInfo().get(0).getString("type");
		}
		cmdLineOptions.addOption(Option.builder("gl").longOpt("gravity-laws").hasArg()
				.desc("Gravity laws to be used in the simulator. Possible values: " + gravityLawsValues
						+ ". Default value: '" + defaultGravityLawsValue + "'.")
				.build());

		return cmdLineOptions;
	}

	private static void parseModeOption(CommandLine line) throws ParseException {
		String mode = line.getOptionValue("m", _modeDefaultValue);
		switch(mode) {
		case "batch": 
		case "gui": ; break;
		default: throw new ParseException("Invalid mode: " + mode);
		}
		_mode = mode;
	}

	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	private static void parseInFileOption(CommandLine line) throws ParseException {
		_inFile = line.getOptionValue("i");
		if (_inFile == null && _mode.equals("batch")) {
			throw new ParseException("An input file of bodies is required");
		}
	}

	private static void parseStepsOption(CommandLine line) throws ParseException {
		try {
			_steps = Integer.parseInt(line.getOptionValue("s"));
		} catch(NumberFormatException e) {
			throw new ParseException("Wrong steps");
		}
	}

	private static void parseOutFileOption(CommandLine line) throws ParseException {
		_outFile = line.getOptionValue("o");
	}

	private static void parseDeltaTimeOption(CommandLine line) throws ParseException {
		String dt = line.getOptionValue("dt", _dtimeDefaultValue.toString());
		try {
			_dtime = Double.parseDouble(dt);
			assert (_dtime > 0);
		} catch (Exception e) {
			throw new ParseException("Invalid delta-time value: " + dt);
		}
	}

	private static void parseGravityLawsOption(CommandLine line) throws ParseException {

		// this line is just a work around to make it work even when _gravityLawsFactory
		// is null, you can remove it when've defined _gravityLawsFactory
		if (_gravityLawsFactory == null)
			return;

		String gl = line.getOptionValue("gl");
		if (gl != null) {
			for (JSONObject fe : _gravityLawsFactory.getInfo()) {
				if (gl.equals(fe.getString("type"))) {
					_gravityLawsInfo = fe;
					break;
				}
			}
			if (_gravityLawsInfo == null) {
				throw new ParseException("Invalid gravity laws: " + gl);
			}
		} else {
			_gravityLawsInfo = _gravityLawsFactory.getInfo().get(0);
		}
	}

	private static void startBatchMode() throws Exception {
		// create and connect components, then start the simulator
		InputStream is = new FileInputStream(new File(_inFile));
		OutputStream os = _outFile == null ? System.out : new FileOutputStream(new File(_outFile));
		
		try {
			PhysicsSimulator sim = new PhysicsSimulator(_dtime, _gravityLawsFactory.createInstance(_gravityLawsInfo));
			Controller cont = new Controller(sim, _bodyFactory, _gravityLawsFactory);
		
			cont.loadBodies(is);
			cont.run(_steps, os);
		} catch (IllegalArgumentException e){
			System.err.println("Illegal argument: " + e.getMessage());
		}
	}


	private static void startGUIMode() throws Exception {
		// create and connect components, then start the simulator
//		OutputStream os = _outFile == null ? System.out : new FileOutputStream(new File(_outFile));
		try {
			PhysicsSimulator sim = new PhysicsSimulator(_dtime, _gravityLawsFactory.createInstance(_gravityLawsInfo));
			Controller cont = new Controller(sim, _bodyFactory, _gravityLawsFactory);
			
			if(_inFile != null) cont.loadBodies(new FileInputStream(new File(_inFile)));
			
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					new MainWindow(cont);
				}
			});
			
		} catch (IllegalArgumentException e){
			System.err.println("Illegal argument: " + e.getMessage());
		}
	}

	private static void start(String[] args) throws Exception {
		parseArgs(args);
		
		switch(_mode) {
		case "batch": startBatchMode(); break;
		case "gui": startGUIMode(); break;
		}	
	}

	public static void main(String[] args) {
		try {
			init();
			start(args);
		} catch (Exception e) {
			System.err.println("Something went wrong ...");
			System.err.println();
			e.printStackTrace();
		}
	}
}
