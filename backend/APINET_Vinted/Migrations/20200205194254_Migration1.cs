using Microsoft.EntityFrameworkCore.Migrations;

namespace APINET_Vinted.Migrations
{
    public partial class Migration1 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<bool>(
                name: "Activo",
                table: "Productos",
                nullable: false,
                defaultValue: false);

            migrationBuilder.AddColumn<int>(
                name: "Puntuacion",
                table: "Productos",
                nullable: false,
                defaultValue: 0);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Activo",
                table: "Productos");

            migrationBuilder.DropColumn(
                name: "Puntuacion",
                table: "Productos");
        }
    }
}
