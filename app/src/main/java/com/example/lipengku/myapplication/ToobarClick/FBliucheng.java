package com.example.lipengku.myapplication.ToobarClick;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lipengku.myapplication.MainActivity;
import com.example.lipengku.myapplication.R;

public class FBliucheng extends AppCompatActivity {
    private TextView xuzhi,zhuyi,geshi,yaoqiu;
    private ImageView add;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fabiaoliucheng);
        xuzhi = findViewById(R.id.xuzhi);
        zhuyi = findViewById(R.id.zhuyi);
        geshi = findViewById(R.id.ziliaogeshi);
        yaoqiu = findViewById(R.id.yaoqiu);
        add = findViewById(R.id.iv_plus);
        xuzhi.setText("\n" +
                "1、来稿请写明文献的作者、工作单位、邮编、E-mail以及联系电话：文献最好以word文件格式 发送给本刊。\n" +
                "\n" +
                "2、来稿就能反应该学术领域的最新进展水平。论点明确，论据充分，数据可靠，条理清晰，题文相符，文字简明。文献已2500字符左右为宜。\n" +
                "\n" +
                "3、本刊收到稿件后，在1-3个工作日内把本刊对稿件的意见通知给作者。如稿件被录用，目前一般在1-3个月左右可刊发出版，具体刊期可咨询相关编辑。\n" +
                "\n" +
                "4、晋升职称需要的文献，建议您在使用此文献的前6个月投来稿伯，这样此文献在您晋升职称前刊登的几率很大。\n");
        zhuyi.setText("投稿邮件标题格式：姓名 - 投稿刊物名 - 文献标题 - 日期。\n" +
                "\n" +
                "如：张三--《管理学家》--试论唯物主义的主观精神形态---20140701\n" +
                "\n" +
                "（请务必投搞稿邮件标题格式来投稿，否则不给予处理)");
        geshi.setText("\n" +
                "作者资料：姓名、单位、单位所在城市、单位邮编、详细通讯地址、收件人、邮编、联系电话、简介。\n" +
                "\n" +
                "简介格式：姓名，（19**-），性别，**省**市（籍贯），（职称），博士（学历），研究方向：******。\n" +
                "\n" +
                "例：作者简介：张三（1969-），女（汉族），天津市，* * *科技学院教授，马克思主义哲学。\n");
        yaoqiu.setText("1、选题新颖，观点明确，论据充实，论证严密，语言精炼，资料可靠，能及时反映教育学术研究和教育教学改革实践的最新成果。尤为欢迎有新观点、新方法、新视角的特色稿件和专家稿件。对于有重大学术理论创新的文稿，本刊将优先发表，稿酬从优。\n" +
                "\n" +
                "2、文稿书写格式及要求。来稿请按如下顺序撰写：文题、作者姓名、摘要（不超过200字）、关键词、作者简介（真实姓名、出生年份、性别、籍贯、工作单位、职务、职称、专业学位和研究方向以及工作单位所在省、市、邮编）、中图分类号、文献标识码、正文、注释、参考文献及英文题目和作者拼音名。若有基金资助或课题经费资助的文献，请在[作者简介]后用[基金项目]注明基金项目名称或课题项目名称及编号。\n" +
                "\n" +
                "    > 文题：应简明确切地反映文章的特定内容，以不超过2-字为宜，一般不用副标题。\n" +
                "    > 关键词：2~5个，采用教育教学标准主题词，若规定标准词表中无该关键词的可使用自由词。\n" +
                "    > 中图分类号、文献标识码：此两项共占一行。例如：[中图分类号]G42[文献标识码]A\n" +
                "    > 图表：凡文字能说明的内容尽量不用表和图。文中的表或图应各有表题、图题。同时必须有相应的表序号和图序号。表中需说明的问题采用*，**，***表示，并置于表的下方加“注：......”。做图要规范，图坐标要设计准确，刻度均匀。\n" +
                "    > 名词使用：文中所用专用名词不要随意缩写，如所用名词过长，而文中又需多次使用，则应在第一次使用时在全名后加圆括号注明缩写。\n" +
                "    > 计量单位：使用我国法定计量单位。标点符号、数字用法等均按国家标准执行。\n" +
                "    > 标题序号：可按四级小标题的格式写：“一、”“（一）”“1、”“（1）”；一级、二级标题另起段，正文另起段；三级、四级小标题不另起段，与正文接排。\n" +
                "    > 注释：注释是对正文中某一特定内容的诠释和说明。用数字加圆圈标注如①②……，置于文后，标注文献类型。中文注释须依次标明序号（外加圆圈）、作者、书名、出版地、出版社、出版年份、页码；外文注释须依次标明序号（外加圆圈）、作者姓、作者名、书名、出版地、出版社、出版年份、页码。\n" +
                "    > 参考文献：所列参考文献只限作者亲自阅读过的近期公开出版的主要文献。按文中首次出现的次序编号，内部刊物或未公开发表的资料均不列入。参考文献置于注释之后。标注文献类型。中文参考文献须依次标明序号（外加方括号）、作者、书名、出版地、出版社、出版年份；外文参考文献须依次标明序号（外加方括号）、作者姓、作者名、书名、出版地、出版社、出版年份。既有中文文献又有外文文献的，请按中文在前、外文在后的顺序分别排列，并以第一作者姓氏的汉语拼音及外文字母为序；外文书名及刊名请用斜体字。若引用文章为电子文献，请注明网络地址及时间。\n" +
                "\n");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FBliucheng.this, Add.class);
                startActivity(intent);
            }
        });
    }
}
