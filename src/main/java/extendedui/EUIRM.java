package extendedui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import eatyourbeets.interfaces.delegates.FuncT2;
import extendedui.ui.TextureCache;

import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class EUIRM
{
    public static final String ID = "extendedui";
    public static Images Images = new Images();
    public static Strings Strings;
    protected static final HashMap<String, Texture> internalTextures = new HashMap<>();
    private static final HashMap<String, Texture> localTextures = new HashMap<>();

    public static void Initialize() {
        Strings = new Strings();
    }

    public static Texture GetTexture(String path) {
        return GetTexture(path, false);
    }

    public static Texture GetTexture(String path, boolean useMipMap) {
        return GetTexture(path, true, false, false);
    }

    public static Texture GetTexture(String path, boolean useMipMap, boolean refresh, boolean suppressError) {
        Texture texture = internalTextures.get(path);
        if (texture == null || refresh) {
            texture = LoadTextureImpl(Gdx.files.internal(path), useMipMap, suppressError);
            internalTextures.put(path, texture);
        }

        return texture;
    }

    public static Texture GetLocalTexture(String path) {
        return GetLocalTexture(path, false);
    }

    public static Texture GetLocalTexture(String path, boolean useMipMap) {
        return GetLocalTexture(path, true, false, false);
    }

    public static Texture GetLocalTexture(String path, boolean useMipMap, boolean refresh, boolean suppressError) {
        Texture texture = localTextures.get(path);
        if (texture == null || refresh) {
            texture = LoadTextureImpl(Gdx.files.local(path), useMipMap, suppressError);
            localTextures.put(path, texture);
        }

        return texture;
    }

    private static Texture LoadTextureImpl(FileHandle file, boolean useMipMap, boolean suppressError) {
        if (file.exists()) {
            Texture texture = new Texture(file, useMipMap);
            if (useMipMap) {
                texture.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
            } else {
                texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            }
            return texture;
        }
        else {
            if (suppressError)
            {
                EUIUtils.LogInfoIfDebug(EUIRM.class, "Texture does not exist: " + file.path());
            }
            else
            {
                EUIUtils.LogError(EUIRM.class, "Texture does not exist: " + file.path());
            }

        }
        return null;
    }

    public static String GetID(String suffix)
    {
        return ID + ":" + suffix;
    }

    private static UIStrings GetUIStrings(String suffix)
    {
        return CardCrawlGame.languagePack.getUIString(GetID(suffix));
    }

    public static class Images {
        public final TextureCache Base_Badge                  = new TextureCache("images/extendedui/tooltip/Base_Badge.png");
        public final TextureCache Base_Border                 = new TextureCache("images/extendedui/tooltip/Base_Border.png");
        public final TextureCache Arrow                       = new TextureCache("images/extendedui/ui/Arrow.png");
        public final TextureCache Border                      = new TextureCache("images/extendedui/ui/Border.png");
        public final TextureCache CardPool                    = new TextureCache("images/extendedui/ui/CardPool.png");
        public final TextureCache CardPool_Pride              = new TextureCache("images/extendedui/ui/CardPool2.png");
        public final TextureCache Divider                     = new TextureCache("images/extendedui/ui/Divider.png");
        public final TextureCache Draggable                   = new TextureCache("images/extendedui/ui/Draggable.png");
        public final TextureCache FileSelectButton           = new TextureCache("images/extendedui/ui/FileSelectButton.png");
        public final TextureCache FullSquare                  = new TextureCache("images/extendedui/ui/FullSquare.png");
        public final TextureCache HexagonalButton             = new TextureCache("images/extendedui/ui/HexagonalButton.png");
        public final TextureCache HexagonalButtonBorder       = new TextureCache("images/extendedui/ui/HexagonalButtonBorder.png");
        public final TextureCache HexagonalButtonHover        = new TextureCache("images/extendedui/ui/HexagonalButtonHover.png");
        public final TextureCache LongButton                  = new TextureCache("images/extendedui/ui/LongButton.png");
        public final TextureCache LongButtonBorder            = new TextureCache("images/extendedui/ui/LongButtonBorder.png");
        public final TextureCache Minus                       = new TextureCache("images/extendedui/ui/Minus.png");
        public final TextureCache Panel                       = new TextureCache("images/extendedui/ui/Panel.png");
        public final TextureCache Panel_Elliptical            = new TextureCache("images/extendedui/ui/Panel_Elliptical.png");
        public final TextureCache Panel_Elliptical_Half_H     = new TextureCache("images/extendedui/ui/Panel_Elliptical_Half_H.png");
        public final TextureCache Panel_Large                 = new TextureCache("images/extendedui/ui/Panel_Large.png");
        public final TextureCache Panel_Rounded               = new TextureCache("images/extendedui/ui/Panel_Rounded.png");
        public final TextureCache Panel_Rounded_Half_H        = new TextureCache("images/extendedui/ui/Panel_Rounded_Half_H.png");
        public final TextureCache Plus                        = new TextureCache("images/extendedui/ui/Plus.png");
        public final TextureCache RectangularButton           = new TextureCache("images/extendedui/ui/RectangularButton.png");
        public final TextureCache SquaredButton               = new TextureCache("images/extendedui/ui/SquaredButton.png");
        public final TextureCache Tag                         = new TextureCache("images/extendedui/ui/Tag.png");
        public final TextureCache X                           = new TextureCache("images/extendedui/ui/X.png");
    }

    public static class Strings {
        private final UIStrings StringsConfig = GetUIStrings("config");
        private final UIStrings StringsGrammar = GetUIStrings("grammar");
        private final UIStrings StringsHotkeys = GetUIStrings("hotkeys");
        private final UIStrings StringsMisc = GetUIStrings("misc");
        private final UIStrings StringsUIPool = GetUIStrings("ui_pool");
        private final UIStrings StringsUIFilter = GetUIStrings("ui_filters");

        public final String Config_UseVanillaCompendium = StringsConfig.TEXT[0];
        public final String Config_DisableEffekseer = StringsConfig.TEXT[1];
        public final String Config_FlushOnGameStart = StringsConfig.TEXT[2];
        public final String Config_FlushOnRoomStart = StringsConfig.TEXT[3];
        public final String Config_ShowModSettings = StringsConfig.TEXT[4];
        public final String Config_UseSeparateFonts = StringsConfig.TEXT[5];
        public final String Config_OverrideGameFont = StringsConfig.TEXT[6];
        public final String Config_MainFont = StringsConfig.TEXT[7];
        public final String Config_CardDescFont = StringsConfig.TEXT[8];
        public final String Config_CardTitleFont = StringsConfig.TEXT[9];
        public final String Config_TipDescFont = StringsConfig.TEXT[10];
        public final String Config_TipTitleFont = StringsConfig.TEXT[11];
        //public final String Config_BannerFont = StringsConfig.TEXT[12];
        //public final String Config_ResetTooltips = StringsConfig.TEXT[13];

        public final String Hotkey_Cycle = StringsHotkeys.TEXT[0];
        public final String Hotkey_OpenCardPool = StringsHotkeys.TEXT[1];
        public final String Hotkey_Toggle = StringsHotkeys.TEXT[2];

        public final String Misc_KeyToCycle = StringsMisc.TEXT[0];
        public final String Misc_TypeToSearch = StringsMisc.TEXT[1];
        public final String Misc_SortByCount = StringsMisc.TEXT[2];
        public final String Misc_Clear = StringsMisc.TEXT[3];
        public final String Misc_ExtraSettings = StringsMisc.TEXT[4];
        public final String Misc_EffekseerSettings = StringsMisc.TEXT[5];
        public final String Misc_FontSettings = StringsMisc.TEXT[6];
        public final String Misc_RestartRequired = StringsMisc.TEXT[7];
        public final String Misc_FontDescription = StringsMisc.TEXT[8];

        public final String UIPool_ViewPool = StringsUIPool.TEXT[0];
        public final String UIPool_ViewPoolDescription = StringsUIPool.TEXT[1];
        public final String UIPool_ViewCardPool = StringsUIPool.TEXT[2];
        public final String UIPool_ViewRelicPool = StringsUIPool.TEXT[3];
        public final String UICardPool_ShowColorless = StringsUIPool.TEXT[4];

        public final String UI_ItemsSelected = StringsUIFilter.TEXT[0];
        public final String UI_Keywords = StringsUIFilter.TEXT[1];
        public final String UI_Amount = StringsUIFilter.TEXT[2];
        public final String UI_Origins = StringsUIFilter.TEXT[3];
        public final String UI_Colors = StringsUIFilter.TEXT[4];
        public final String UI_Total = StringsUIFilter.TEXT[5];
        public final String UI_Filters = StringsUIFilter.TEXT[6];
        public final String UI_Any = StringsUIFilter.TEXT[7];
        public final String UI_NoMatch = StringsUIFilter.TEXT[8];
        public final String UI_BaseGame = StringsUIFilter.TEXT[9];
        public final String UI_NameSearch = StringsUIFilter.TEXT[10];
        public final String UI_DescriptionSearch = StringsUIFilter.TEXT[11];
        public final String UI_Basic = StringsUIFilter.TEXT[12];
        public final String UI_Seen = StringsUIFilter.TEXT[13];
        public final String UI_Unseen = StringsUIFilter.TEXT[14];

        public final String KeyToCycle(String keyName) {
            return EUIUtils.Format(Misc_KeyToCycle, keyName);
        }
        public final String SortBy(String item) {
            return EUIUtils.Format(Misc_SortByCount, item);
        }

        // e.g. English: Red Card -> 0 1, Spanish: Carta roja -> 1 0
        public final String AdjNoun(Object adj, Object noun) {
            return EUIUtils.Format(StringsGrammar.TEXT[0], adj, noun);
        }

        // e.g. English: Two Cards -> 0 1, Spanish: Dos cartas -> 0 1
        public final String NumNoun(Object verb, Object noun) {
            return EUIUtils.Format(StringsGrammar.TEXT[1], verb, noun);
        }

        // e.g. English: Discard Cards -> 0 1, Spanish: Descarta cartas -> 0 1
        public final String VerbNoun(Object verb, Object noun) {
            return EUIUtils.Format(StringsGrammar.TEXT[2], verb, noun);
        }

        // e.g. English: Cards discarded -> 0 1, Spanish: Cartas descartada -> 0 1
        public final String NounVerb(Object verb, Object noun) {
            return EUIUtils.Format(StringsGrammar.TEXT[3], verb, noun);
        }

        // e.g. English: Card #2 -> 0 1, Spanish: Carta #2 -> 0 1
        public final String Generic2(Object noun, Object number) {
            return EUIUtils.Format(StringsGrammar.TEXT[4], noun, number);
        }

        // e.g. English: Two Red Cards -> 0 1 2, Spanish: Dos Cartas rojas -> 0 2 1
        public final String NumAdjNoun(Object num, Object adj, Object noun) {
            return EUIUtils.Format(StringsGrammar.TEXT[5], num, adj, noun);
        }

        // e.g. English: Two Cards In Hand, Spanish: Dos cartas en la mano
        public final String NumNounPlace(Object num, Object noun, Object place) {
            return EUIUtils.Format(StringsGrammar.TEXT[6], num, noun, place);
        }

        // e.g. English: Discard Red Cards, Spanish: Descarta cartas rojas
        public final String VerbAdjNoun(Object verb, Object adj, Object noun) {
            return EUIUtils.Format(StringsGrammar.TEXT[7], verb, adj, noun);
        }

        // e.g. English: Discard Two Cards, Spanish: Descarta dos cartas
        public final String VerbNumNoun(Object verb, Object num, Object noun) {
            return EUIUtils.Format(StringsGrammar.TEXT[8], verb, num, noun);
        }

        // e.g. English: Discard the Cards Recklessly -> 0 1 2, Spanish: Descarta imprudentemente las cartas -> 0 2 1
        public final String VerbNounAdv(Object verb, Object adj, Object noun) {
            return EUIUtils.Format(StringsGrammar.TEXT[9], verb, adj, noun);
        }

        // e.g. English: Two Red Cards In Hand, Spanish: Dos cartas rojas en la mano
        public final String NumAdjNounPlace(Object num, Object adj, Object noun, Object place) {
            return EUIUtils.Format(StringsGrammar.TEXT[10], num, adj, noun, place);
        }

        // e.g. English: Discard Two Cards In Hand, Spanish: Descarta dos cartas en la mano
        public final String VerbNumNounPlace(Object num, Object adj, Object noun, Object place) {
            return EUIUtils.Format(StringsGrammar.TEXT[11], num, adj, noun, place);
        }

        // e.g. English: Discard Two Red Cards In Hand, Spanish: Descarta dos cartas rojas en la mano
        public final String VerbNumAdjNounPlace(Object verb, Object num, Object adj, Object noun, Object place) {
            return EUIUtils.Format(StringsGrammar.TEXT[12], verb, num, adj, noun, place);
        }

        // e.g. English: O1 and O2, Spanish: O1 y O2
        public final String And(Object obj1, Object obj2) {return EUIUtils.Format(StringsGrammar.TEXT[13], obj1, obj2);}

        // e.g. English: O1 or O2, Spanish: O1 o O2
        public final String Or(Object obj1, Object obj2) {return EUIUtils.Format(StringsGrammar.TEXT[14], obj1, obj2);}

        // e.g. English: Not O1, Spanish: No 01
        public final String Not(Object obj1) {return EUIUtils.Format(StringsGrammar.TEXT[15], obj1);}

        // e.g. English: Card -> Cards, Spanish: Carta -> Cartas
        public final String Plural(Object obj) {
            String base = String.valueOf(obj);
            return EUIUtils.Format(StringsGrammar.EXTRA_TEXT[0], obj);
        }

        // e.g. English: Card -> Card(s)
        public final String PluralC(Object obj) {return EUIUtils.Format(StringsGrammar.EXTRA_TEXT[1], obj);}

        // e.g. English: Discard -> Discarded, Spanish: Descarta -> Descartada
        public final String Past(Object obj) {return EUIUtils.Format(StringsGrammar.EXTRA_TEXT[2], obj);}

        public final String Present(Object obj) {return EUIUtils.Format(StringsGrammar.EXTRA_TEXT[3], obj);}

        public final String JoinWithAnd(List<String> values) {
            return JoinWith(this::And, values);
        }
        public final String JoinWithAnd(String... values) {
            return JoinWith(this::And, values);
        }
        public final String JoinWithOr(List<String> values) {
            return JoinWith(this::Or, values);
        }
        public final String JoinWithOr(String... values) {
            return JoinWith(this::Or, values);
        }

        public final String JoinWith(FuncT2<String, String, String> strFunc, List<String> values) {
            if (values.size() == 0) {
                return "";
            }
            if (values.size() == 1) {
                return values.get(0);
            }
            StringJoiner sj = new StringJoiner(", ");

            int i;
            for (i = 0; i < values.size() - 1; i++) {
                sj.add(values.get(i));
            }

            return strFunc.Invoke(sj.toString(), values.get(i));
        }
        public final String JoinWith(FuncT2<String, String, String> strFunc, String... values) {
            if (values.length == 0) {
                return "";
            }
            if (values.length == 1) {
                return values[0];
            }
            StringJoiner sj = new StringJoiner(", ");
            int var4 = values.length;

            int i;
            for (i = 0; i < values.length - 1; i++) {
                sj.add(values[i]);
            }

            return strFunc.Invoke(sj.toString(), values[i]);
        }
    }
}
