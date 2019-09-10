package com.exsoinn.practice.algorithm;

/**
 * @author josequijada
 */
public class CanSpell {

  /*
  I gotta feeling that

 tonight’s

gonna be a

  good night


canSpell(“goog”, “I gotta feeling that tonight’s gonna be a good night”); // true



boolean canSpell(String str, String lyric) {

  if (null == lyric || lyric.length== 0) {

    return false;

  }


  String[] words = lyrics.split(‘ ‘);




char[] chars = str.charArray();


List<String> matched = new ArrayList();

StringBuilder sb = new StringBuilder();

for (Character c : chars)

  for (String w : words) {

      if (matched.contains(w) continue;

      String ucWord = w.toUpperCase()

      if (ucWord.indexOf(Character.toUpperCase(C)) => 0) {

        matched.add(w);

        formed.append(c);

      }

   }

}


return str.equals(formed.toString);


} // func






// JSON IN

{

   “toFind”:”goog”,

   “target”:”I gotta feeling that tonight’s gonna be a good night”

}


// JSON OUT

{ “result”: true}




public class StringFinderController {


@RequestMapping(value = “/contains/string”, method = RequestMethod.POST)

@ResponseBody()

public EntityResponse findString(@RequestBody StringFinderRequest params) {

   String arg1 = params.getString();


  String arg2 = params.getTarget();

  StringFinderResponse res = new StringFinderResponse();

  res.setResult(canSpell(arg1, arg2);

  return ResponseEntity.ok.body(res);

}

}
   */

}
