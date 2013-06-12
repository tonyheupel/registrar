require 'guard/java_translator'

guard(:java, { :project_name   => 'Registrar',
              :all_after_pass => false,
              :focused_cli    => 'ant compile-tests',
              :all_cli        => 'ant',
              :classpath      => './build/classes:./build/classes-test:./lib/*' }) do
  watch (%r{^test/*/(.+)\.java$}) { |m| ::Guard::JavaTranslator.filename_to_classname(m[0], 'test/') }  # test file changes

  watch(%r{^src/*/(.+)\.java$}) { |m|
     test_filename = "test/#{m[1]}Test.java"
     ::Guard::JavaTranslator.filename_to_classname(test_filename, 'test/')
   } # when source files change, run the test for that file

  # ignore(path) will ignore files that change automatically, such as generated code filesjt
end
