package har2jmeter

def cli = new CliBuilder(usage: 'har2JMeter -har [*.har] -jmx [*.jmx]', header: 'Options:')
cli.help('print this message.')
cli.har(args: 1, 'The har input file which could be converted to a JMeter JMX file.')
cli.jmx(args: 2, 'The jmx output file.')
cli.header(args: 3, 'Add the request header to the JMeter requests true or false (default true).')

def options = cli.parse(args)
if (options.help || !options.har || !options.jmx) {
    cli.usage()
    return;
}
def withHttpHeaders = options.header ?: true

try {
    def har2JMeter = new Har2JMeter(withHttpHeaders: withHttpHeaders)
    har2JMeter.convert(new File(options.har), new File(options.jmx))
} catch (Throwable exp) {
    println exp.message
}

